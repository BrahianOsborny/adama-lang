/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.net.client;

import io.netty.buffer.ByteBuf;
import org.adamalang.ErrorCodes;
import org.adamalang.common.*;
import org.adamalang.common.net.ByteStream;
import org.adamalang.common.net.ChannelClient;
import org.adamalang.common.net.NetBase;
import org.adamalang.common.queue.ItemAction;
import org.adamalang.common.queue.ItemQueue;
import org.adamalang.net.client.bidi.DocumentExchange;
import org.adamalang.net.client.bidi.MeteringExchange;
import org.adamalang.net.client.contracts.*;
import org.adamalang.net.codec.ClientCodec;
import org.adamalang.net.codec.ClientMessage;
import org.adamalang.net.codec.ServerCodec;
import org.adamalang.net.codec.ServerMessage;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/** a managed client that makes talking to the gRPC server nice */
public class InstanceClient implements AutoCloseable {
  private final NetBase base;
  public final String target;
  private final SimpleExecutor executor;
  private final ClientMetrics metrics;
  private final HeatMonitor monitor;
  private final Lifecycle lifecycle;
  private final Random rng;
  private final ExceptionLogger logger;
  private final AtomicBoolean alive;
  private int backoff;
  private ItemQueue<ChannelClient> client;

  public InstanceClient(NetBase base, ClientMetrics metrics, HeatMonitor monitor, String target, SimpleExecutor executor, Lifecycle lifecycle, ExceptionLogger logger) throws Exception {
    this.base = base;
    this.target = target;
    this.executor = executor;
    this.metrics = metrics;
    this.monitor = monitor;
    this.rng = new Random();
    this.client = new ItemQueue<>(executor, 64, 2500);
  //  this.downstream = null;
    this.lifecycle = lifecycle;
    this.logger = logger;
    this.alive = new AtomicBoolean(true);
    this.backoff = 1;
    retryConnection();
  }

  private void retryConnection() {
    if (alive.get()) {
      base.connect(this.target, new org.adamalang.common.net.Lifecycle() {
        @Override
        public void connected(ChannelClient channel) {
          channel.open(new ServerCodec.StreamInfo() {
            @Override
            public void handle(ServerMessage.HeatPayload heat) {
              monitor.heat(target, heat.cpu, heat.mem);
            }

            @Override
            public void handle(ServerMessage.InventoryHeartbeat payload) {
              // TODO: route inventory, yay?
            }

            @Override
            public void completed() {

            }

            @Override
            public void error(int errorCode) {

            }
          }, new Callback<ByteStream>() {
            @Override
            public void success(ByteStream value) {
              if (monitor != null) {
                ByteBuf buf = value.create(8);
                ClientCodec.write(buf, new ClientMessage.RequestHeat());
                value.next(buf);
              }
              {
                ByteBuf buf = value.create(8);
                ClientCodec.write(buf, new ClientMessage.RequestInventoryHeartbeat());
                value.next(buf);
              }
            }

            @Override
            public void failure(ErrorCodeException ex) {
              // SHOULD NOT HAPPEN unless disconnected, need to inst
              // TODO: instrument
            }
          });

          executor.execute(new NamedRunnable("channel-client-ready") {
            @Override
            public void execute() throws Exception {
              backoff = 1;
              if (alive.get()) {
                client.ready(channel);
              }
            }
          });
        }

        @Override
        public void failed(ErrorCodeException ex) {
          executor.execute(new NamedRunnable("channel-client-failed") {
            @Override
            public void execute() throws Exception {
              client.unready();
              scheduleWithinExecutor();
            }
          });
        }

        @Override
        public void disconnected() {
          executor.execute(new NamedRunnable("channel-client-disconnect") {
            @Override
            public void execute() throws Exception {
              client.unready();
              scheduleWithinExecutor();
            }
          });
        }

        private void scheduleWithinExecutor() {
          backoff = (int) (1 + backoff * Math.random());
          if (backoff > 2000) {
            backoff = (int) (1500 + 500 * Math.random());
          }
          executor.schedule(new NamedRunnable("client-retry") {
            @Override
            public void execute() throws Exception {
              retryConnection();
            }
          }, backoff);
        }
      });
    }
  }

  /** block the current thread to ensure the client is connected right now */
  public boolean ping(int timeLimit) throws Exception {
    AtomicBoolean success = new AtomicBoolean(false);
    CountDownLatch latch = new CountDownLatch(1);
    executor.execute(new NamedRunnable("execute-ping") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<>(ErrorCodes.ADAMA_NET_PING_TIMEOUT, ErrorCodes.ADAMA_NET_PING_REJECTED, metrics.client_ping.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            client.open(new ServerCodec.StreamPing() {
              @Override
              public void handle(ServerMessage.PingResponse payload) {
                success.set(true);
              }

              @Override
              public void completed() {
                latch.countDown();
              }

              @Override
              public void error(int code) {
                latch.countDown();
              }
            }, new Callback<ByteStream>() {
              @Override
              public void success(ByteStream stream) {
                ByteBuf toWrite = stream.create(4);
                ClientCodec.write(toWrite, new ClientMessage.PingRequest());
                stream.next(toWrite);
                stream.completed();
              }

              @Override
              public void failure(ErrorCodeException ex) {
                latch.countDown();
              }
            });
          }

          @Override
          protected void failure(int code) {
            latch.countDown();
          }
        }, timeLimit);
      }
    });
    latch.await(timeLimit, TimeUnit.MILLISECONDS);
    return success.get();
  }

  /** create a document */
  public void create(String origin, String agent, String authority, String space, String key, String entropy, String arg, Callback<Void> callback) {
    executor.execute(new NamedRunnable("execute-create") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_CREATE_TIMEOUT, ErrorCodes.ADAMA_NET_CREATE_REJECTED, metrics.client_create.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            client.open(new ServerCodec.StreamCreation() {
              @Override
              public void handle(ServerMessage.CreateResponse payload) {
                callback.success(null);
              }

              @Override
              public void completed() {
              }

              @Override
              public void error(int errorCode) {
                callback.failure(new ErrorCodeException(errorCode));
              }
            }, new Callback<ByteStream>() {
              @Override
              public void success(ByteStream stream) {
                ByteBuf toWrite = stream.create(agent.length() + authority.length() + space.length() + key.length() + entropy.length() + arg.length() + origin.length() + 40);
                ClientMessage.CreateRequest create = new ClientMessage.CreateRequest();
                create.origin = origin;
                create.agent = agent;
                create.authority = authority;
                create.space = space;
                create.key = key;
                create.entropy = entropy;
                create.arg = arg;
                ClientCodec.write(toWrite, create);
                stream.next(toWrite);
                stream.completed();
              }

              @Override
              public void failure(ErrorCodeException ex) {
                callback.failure(ex);
              }
            });
          }

          @Override
          protected void failure(int code) {
            callback.failure(new ErrorCodeException(code));
          }
        });
      }
    });
  }

  public void reflect(String space, String key, Callback<String> callback) {
    executor.execute(new NamedRunnable("execute-reflect") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_CREATE_TIMEOUT, ErrorCodes.ADAMA_NET_CREATE_REJECTED, metrics.client_create.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            client.open(new ServerCodec.StreamReflection() {
              @Override
              public void handle(ServerMessage.ReflectResponse payload) {
                callback.success(payload.schema);
              }

              @Override
              public void completed() {

              }

              @Override
              public void error(int errorCode) {
                callback.failure(new ErrorCodeException(errorCode));
              }
            }, new Callback<ByteStream>() {
              @Override
              public void success(ByteStream stream) {
                ByteBuf toWrite = stream.create(space.length() + key.length() + 10);
                ClientMessage.ReflectRequest reflect = new ClientMessage.ReflectRequest();
                reflect.space = space;
                reflect.key = key;
                ClientCodec.write(toWrite, reflect);
                stream.next(toWrite);
                stream.completed();
              }

              @Override
              public void failure(ErrorCodeException ex) {
                callback.failure(ex);
              }
            });
          }

          @Override
          protected void failure(int code) {
            callback.failure(new ErrorCodeException(code));
          }
        });
      }
    });
  }

  public void startMeteringExchange(MeteringStream meteringStream) {
    executor.execute(new NamedRunnable("metering-exchange") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_METERING_TIMEOUT, ErrorCodes.ADAMA_NET_METERING_REJECTED, metrics.client_metering_exchange.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            MeteringExchange exchange = new MeteringExchange(target, meteringStream);
            client.open(exchange, exchange);
          }

          @Override
          protected void failure(int code) {
            meteringStream.failure(code);
          }
        });
      }
    });
  }

  public void scanDeployments(String space, Callback<Void> callback) {
    executor.execute(new NamedRunnable("execute-scan") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_SCAN_DEPLOYMENT_TIMEOUT, ErrorCodes.ADAMA_NET_SCAN_DEPLOYMENT_REJECTED, metrics.client_scan_deployment.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            client.open(new ServerCodec.StreamDeployment() {
              @Override
              public void handle(ServerMessage.ScanDeploymentResponse payload) {
                callback.success(null);
              }

              @Override
              public void completed() {

              }

              @Override
              public void error(int errorCode) {
                callback.failure(new ErrorCodeException(errorCode));
              }
            }, new Callback<ByteStream>() {
              @Override
              public void success(ByteStream stream) {
                ByteBuf toWrite = stream.create(space.length() + 10);
                ClientMessage.ScanDeploymentRequest scan = new ClientMessage.ScanDeploymentRequest();
                scan.space = space;
                ClientCodec.write(toWrite, scan);
                stream.next(toWrite);
                stream.completed();
              }

              @Override
              public void failure(ErrorCodeException ex) {
                callback.failure(ex);
              }
            });
          }

          @Override
          protected void failure(int code) {
            callback.failure(new ErrorCodeException(code));
          }
        });
      }
    });
  }

  /** connect to a document */
  public void connect(String agent, String authority, String space, String key, String viewerState, String origin, Events events) {
    ClientMessage.StreamConnect connectMessage = new ClientMessage.StreamConnect();
    connectMessage.agent = agent;
    connectMessage.authority = authority;
    connectMessage.space = space;
    connectMessage.key = key;
    connectMessage.viewerState = viewerState;
    connectMessage.origin = origin;


    executor.execute(new NamedRunnable("document-exchange") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_CONNECT_DOCUMENT_TIMEOUT, ErrorCodes.ADAMA_NET_CONNECT_DOCUMENT_REJECTED, metrics.client_document_exchange.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            DocumentExchange exchange = new DocumentExchange(connectMessage, events);
            client.open(exchange, exchange);
          }

          @Override
          protected void failure(int code) {
            events.error(code);
          }
        });
      }
    });
  }

  @Override
  public void close() {
    alive.set(false);
    executor.execute(new NamedRunnable("close-connection") {
      @Override
      public void execute() throws Exception {
        client.add(new ItemAction<ChannelClient>(ErrorCodes.ADAMA_NET_CLOSE_TIMEOUT, ErrorCodes.ADAMA_NET_CLOSE_REJECTED, metrics.client_close.start()) {
          @Override
          protected void executeNow(ChannelClient client) {
            client.close();
          }

          @Override
          protected void failure(int code) {
            // don't care
          }
        });
        client.unready();
        client.nuke();
      }
    });
  }
}
