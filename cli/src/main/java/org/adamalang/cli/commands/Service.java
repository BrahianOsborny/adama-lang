/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.cli.commands;

import org.adamalang.api.ApiMetrics;
import org.adamalang.cli.Config;
import org.adamalang.cli.Util;
import org.adamalang.common.*;
import org.adamalang.common.jvm.MachineHeat;
import org.adamalang.common.metrics.NoOpMetricsFactory;
import org.adamalang.extern.AssetUploader;
import org.adamalang.extern.Email;
import org.adamalang.extern.ExternNexus;
import org.adamalang.extern.aws.AWSConfig;
import org.adamalang.extern.aws.AWSMetrics;
import org.adamalang.extern.aws.SES;
import org.adamalang.extern.prometheus.PrometheusDashboard;
import org.adamalang.extern.prometheus.PrometheusMetricsFactory;
import org.adamalang.frontend.BootstrapFrontend;
import org.adamalang.frontend.FrontendConfig;
import org.adamalang.gossip.Engine;
import org.adamalang.gossip.EngineRole;
import org.adamalang.gossip.GossipMetricsImpl;
import org.adamalang.grpc.client.Client;
import org.adamalang.grpc.client.ClientMetrics;
import org.adamalang.grpc.server.Server;
import org.adamalang.grpc.server.ServerMetrics;
import org.adamalang.grpc.server.ServerNexus;
import org.adamalang.mysql.DataBase;
import org.adamalang.mysql.DataBaseConfig;
import org.adamalang.mysql.DataBaseMetrics;
import org.adamalang.mysql.backend.BackendMetrics;
import org.adamalang.mysql.backend.BlockingDataService;
import org.adamalang.mysql.deployments.Deployments;
import org.adamalang.mysql.deployments.data.Deployment;
import org.adamalang.overlord.Overlord;
import org.adamalang.overlord.OverlordMetrics;
import org.adamalang.overlord.grpc.OverlordClient;
import org.adamalang.overlord.grpc.OverlordServer;
import org.adamalang.overlord.html.ConcurrentCachedHttpHandler;
import org.adamalang.runtime.contracts.DeploymentMonitor;
import org.adamalang.runtime.data.Key;
import org.adamalang.runtime.deploy.DeploymentFactoryBase;
import org.adamalang.runtime.deploy.DeploymentPlan;
import org.adamalang.runtime.natives.NtAsset;
import org.adamalang.runtime.sys.CoreMetrics;
import org.adamalang.runtime.sys.CoreService;
import org.adamalang.runtime.sys.metering.DiskMeteringBatchMaker;
import org.adamalang.runtime.sys.metering.MeterReading;
import org.adamalang.runtime.sys.metering.MeteringPubSub;
import org.adamalang.runtime.data.ThreadedDataService;
import org.adamalang.web.contracts.HttpHandler;
import org.adamalang.web.contracts.ServiceBase;
import org.adamalang.web.service.ServiceRunnable;
import org.adamalang.web.service.WebConfig;
import org.adamalang.web.service.WebMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class Service {
  private static final Logger LOGGER = LoggerFactory.getLogger(Service.class);

  public static void execute(Config config, String[] args) throws Exception {
    if (args.length == 0) {
      serviceHelp(new String[0]);
      return;
    }
    String command = Util.normalize(args[0]);
    String[] next = Util.tail(args);
    switch (command) {
      case "auto":
        serviceAuto(config);
        return;
      case "backend":
        serviceBackend(config);
        return;
      case "overlord":
        serviceOverlord(config);
        return;
      case "frontend":
        serviceFrontend(config);
        return;
      case "dashboards":
        dashboards();
        return;
      case "help":
        serviceHelp(next);
        return;
    }
  }

  public static void serviceHelp(String[] next) {
    System.out.println(Util.prefix("Spin up a service.", Util.ANSI.Green));
    System.out.println();
    System.out.println(Util.prefix("USAGE:", Util.ANSI.Yellow));
    System.out.println("    " + Util.prefix("adama service", Util.ANSI.Green) + " " + Util.prefix("[SERVICESUBCOMMAND]", Util.ANSI.Magenta));
    System.out.println();
    System.out.println(Util.prefix("FLAGS:", Util.ANSI.Yellow));
    System.out.println("    " + Util.prefix("--config", Util.ANSI.Green) + "          Supplies a config file path other than the default (~/.adama)");
    System.out.println();
    System.out.println(Util.prefix("SERVICESUBCOMMAND:", Util.ANSI.Yellow));
    System.out.println("    " + Util.prefix("auto", Util.ANSI.Green) + "              The config will decide the role");
    System.out.println("    " + Util.prefix("backend", Util.ANSI.Green) + "           Spin up a gRPC back-end node");
    System.out.println("    " + Util.prefix("frontend", Util.ANSI.Green) + "          Spin up a WebSocket front-end node");
    System.out.println("    " + Util.prefix("overlord", Util.ANSI.Green) + "          Spin up the cluster overlord");
    System.out.println("    " + Util.prefix("dashboards", Util.ANSI.Green) + "        Produce dashboards for prometheus.");
  }

  public static void serviceAuto(Config config) throws Exception {
    String role = config.get_string("role", "none");
    switch (role) {
      case "backend":
        serviceBackend(config);
        return;
      case "overlord":
        serviceOverlord(config);
        return;
      case "frontend":
        serviceFrontend(config);
        return;
      default:
        System.err.println("invalid role:" + role);
    }
  }

  public static void serviceBackend(Config config) throws Exception {
    MachineHeat.install();
    int port = config.get_int("adama_port", 8001);
    int gossipPort = config.get_int("gossip_backend_port", 8002);
    int monitoringPort = config.get_int("monitoring_backend_port", 8003);
    PrometheusMetricsFactory prometheusMetricsFactory = new PrometheusMetricsFactory(monitoringPort);
    int dataThreads = config.get_int("data_thread_count", 32);
    int coreThreads = config.get_int("service_thread_count", 4);
    String identityFileName = config.get_string("identity_filename", "me.identity");
    String billingRootPath = config.get_string("billing_path", "billing");
    MachineIdentity identity = MachineIdentity.fromFile(identityFileName);
    Engine engine = new Engine(identity, TimeSource.REAL_TIME, new HashSet<>(config.get_str_list("bootstrap")), gossipPort, monitoringPort, new GossipMetricsImpl(prometheusMetricsFactory), EngineRole.Node);
    engine.start();
    DeploymentFactoryBase deploymentFactoryBase = new DeploymentFactoryBase();
    DataBase dataBaseBackend = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "backend"), new DataBaseMetrics(prometheusMetricsFactory, "backend"));
    DataBase dataBaseDeployments = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "deployed"), new DataBaseMetrics(prometheusMetricsFactory, "deployed"));
    BackendMetrics backendMetrics = new BackendMetrics(prometheusMetricsFactory);
    ThreadedDataService dataService = new ThreadedDataService(dataThreads, () -> new BlockingDataService(backendMetrics, dataBaseBackend));
    MeteringPubSub meteringPubSub = new MeteringPubSub(TimeSource.REAL_TIME, deploymentFactoryBase);
    CoreMetrics coreMetrics = new CoreMetrics(prometheusMetricsFactory);
    CoreService service = new CoreService(coreMetrics, deploymentFactoryBase, meteringPubSub.publisher(), dataService, TimeSource.REAL_TIME, coreThreads);

    engine.newApp("adama", port, (hb) -> {
      meteringPubSub.subscribe((bills) -> {
        hb.run();
        return true;
      });
    });

    DeploymentMonitor deploymentMonitor = new DeploymentMonitor() {
      @Override
      public void bumpDocument(boolean changed) {

      }

      @Override
      public void witnessException(ErrorCodeException ex) {

      }
    };
    Consumer<String> scanForDeployments = (space) -> {
      try {
        if ("*".equals(space)) {
          ArrayList<Deployment> deployments = Deployments.listSpacesOnTarget(dataBaseDeployments, identity.ip + ":" + port);
          for (Deployment deployment : deployments) {
            try {
              deploymentFactoryBase.deploy(deployment.space, new DeploymentPlan(deployment.plan, (x, y) -> {
              }));
              service.deploy(deploymentMonitor);
            } catch (Exception ex) {
              LOGGER.error("failed-scan-" + deployment.space, ex);
            }
          }
        } else {
          Deployment deployment = Deployments.get(dataBaseDeployments, identity.ip + ":" + port, space);
          deploymentFactoryBase.deploy(deployment.space, new DeploymentPlan(deployment.plan, (x, y) -> {
          }));
          service.deploy(deploymentMonitor);
        }
      } catch (Exception ex) {
        LOGGER.error("failed-scan-" + space, ex);
      }
    };
    File billingRoot = new File(billingRootPath);
    billingRoot.mkdir();
    DiskMeteringBatchMaker billingBatchMaker = new DiskMeteringBatchMaker(TimeSource.REAL_TIME, SimpleExecutor.create("billing-batch-maker"), billingRoot, 10 * 60000L);
    meteringPubSub.subscribe((bills) -> {
      for (MeterReading meterReading : bills) {
        billingBatchMaker.write(meterReading);
      }
      return true;
    });

    // prime the host with spaces
    scanForDeployments.accept("*");
    ServerNexus nexus = new ServerNexus(identity, service, new ServerMetrics(prometheusMetricsFactory), deploymentFactoryBase, scanForDeployments, meteringPubSub, billingBatchMaker, port, 4);

    Server server = new Server(nexus);
    server.start();
    Runtime.getRuntime().addShutdownHook(new Thread(ExceptionRunnable.TO_RUNTIME(new ExceptionRunnable() {
      @Override
      public void run() throws Exception {
        // billingPubSub.terminate();
        // This will send to all connections an empty list which will remove from the routing table. At this point, we should wait all connections migrate away

        // TODO: for each connection, remove from routing table, stop
        server.close();
      }
    })));
  }

  public static void serviceOverlord(Config config) throws Exception {
    MachineHeat.install();
    int gossipPort = config.get_int("gossip_overlord_port", 8010);
    int monitoringPort = config.get_int("monitoring_overlord_port", 8011);
    int overlordPort  = config.get_int("overlord_port", 8015);
    String scanPath = config.get_string("scan_path", "web_root");
    PrometheusMetricsFactory prometheusMetricsFactory = new PrometheusMetricsFactory(monitoringPort);
    DataBase dataBaseDeployments = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "deployed"), new DataBaseMetrics(prometheusMetricsFactory, "deployed"));
    DataBase dataBaseFront = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "frontend"), new DataBaseMetrics(prometheusMetricsFactory, "frontend"));
    DataBase dataBaseBackend = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "backend"), new DataBaseMetrics(prometheusMetricsFactory, "backend"));

    String identityFileName = config.get_string("identity_filename", "me.identity");
    File targetsPath = new File(config.get_string("targets_filename", "targets.json"));
    MachineIdentity identity = MachineIdentity.fromFile(identityFileName);

    Engine engine = new Engine(identity, TimeSource.REAL_TIME, new HashSet<>(config.get_str_list("bootstrap")), gossipPort, monitoringPort, new GossipMetricsImpl(prometheusMetricsFactory), EngineRole.SuperNode);
    engine.start();

    HttpHandler handler = Overlord.execute(identity, engine, overlordPort, prometheusMetricsFactory, targetsPath, dataBaseDeployments, dataBaseFront, dataBaseBackend, scanPath);

    ConfigObject co = new ConfigObject(config.get_or_create_child("overlord_web"));
    co.intOf("http_port", 8081);
    WebConfig webConfig = new WebConfig(co);
    ServiceBase serviceBase = ServiceBase.JUST_HTTP(handler);
    final var runnable = new ServiceRunnable(webConfig, new WebMetrics(prometheusMetricsFactory), serviceBase, () -> {});
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        System.err.println("shutting down overlord");
        runnable.shutdown();
      }
    }));


    System.err.println("running overlord web");
    runnable.run();
    System.err.println("overlord finished");
  }

  public static void serviceFrontend(Config config) throws Exception {
    MachineHeat.install();
    System.err.println("starting frontend");
    String identityFileName = config.get_string("identity_filename", "me.identity");
    int gossipPort = config.get_int("gossip_frontend_port", 8004);
    int monitoringPort = config.get_int("monitoring_frontend_port", 8005);
    MachineIdentity identity = MachineIdentity.fromFile(identityFileName);
    PrometheusMetricsFactory prometheusMetricsFactory = new PrometheusMetricsFactory(monitoringPort);
    DataBase dataBaseFront = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "frontend"), new DataBaseMetrics(prometheusMetricsFactory, "frontend"));
    DataBase dataBaseDeployments = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "deployed"), new DataBaseMetrics(prometheusMetricsFactory, "deployed"));
    DataBase dataBaseBackend = new DataBase(new DataBaseConfig(new ConfigObject(config.read()), "backend"), new DataBaseMetrics(prometheusMetricsFactory, "backend"));
    System.err.println("using databases: " + dataBaseFront.databaseName + ", " + dataBaseDeployments.databaseName + ", and " + dataBaseBackend.databaseName);
    System.err.println("identity: " + identity.ip);
    Engine engine = new Engine(identity, TimeSource.REAL_TIME, new HashSet<>(config.get_str_list("bootstrap")), gossipPort, monitoringPort, new GossipMetricsImpl(prometheusMetricsFactory), EngineRole.Node);
    engine.start();
    System.err.println("gossiping on:" + gossipPort);
    WebConfig webConfig = new WebConfig(new ConfigObject(config.get_or_create_child("web")));
    System.err.println("standing up http on:" + webConfig.port);
    Client client = new Client(identity, new ClientMetrics(prometheusMetricsFactory), null);
    Consumer<Collection<String>> targetPublisher = client.getTargetPublisher();

    engine.subscribe("adama", (targets) -> {
      StringBuilder notice = new StringBuilder();
      boolean append = false;
      for (String target : targets) {
        if (append) {
          notice.append(", ");
        }
        append = true;
        notice.append(target);
      }
      System.err.println("adama targets:" + notice);
      targetPublisher.accept(targets);
    });
    AWSConfig awsConfig = new AWSConfig(new ConfigObject(config.get_or_create_child("aws")));
    // TODO: use S3 and update document core to account for attachment size for billing purposes
    AssetUploader uploader = new AssetUploader() {
      @Override
      public void upload(Key key, NtAsset asset, File localFile, Callback<Void> callback) {
        callback.failure(new ErrorCodeException(-1));
      }
    };
    ConcurrentCachedHttpHandler propigatedHandler = new ConcurrentCachedHttpHandler();

    OverlordClient overlordClient = new OverlordClient(identity, webConfig.port, propigatedHandler);
    engine.subscribe("overlord", new Consumer<Collection<String>>() {
      @Override
      public void accept(Collection<String> targets) {
        for (String first : targets) {
          overlordClient.setTarget(first);
          return;
        }
        overlordClient.setTarget(null);
      }
    });

    Email email = new SES(awsConfig, new AWSMetrics(prometheusMetricsFactory));
    FrontendConfig frontendConfig = new FrontendConfig(new ConfigObject(config.get_or_create_child("saas")));
    Logger accessLog = LoggerFactory.getLogger("access");
    ExternNexus nexus = new ExternNexus(frontendConfig, email, uploader, dataBaseFront, dataBaseDeployments, dataBaseBackend, client, prometheusMetricsFactory, new File("inflight"), (item) -> {
      accessLog.debug(item.toString());
    });
    System.err.println("nexus constructed");
    ServiceBase serviceBase = BootstrapFrontend.make(nexus, propigatedHandler);



    // TODO: have some sense of health checking in the web package
    // TODO: should also have web heat flow to overlord
    AtomicReference<Runnable> heartbeat = new AtomicReference<>();
    CountDownLatch latchForHeartbeat = new CountDownLatch(1);
    engine.newApp("web", webConfig.port, (hb) -> {
      heartbeat.set(hb);
      latchForHeartbeat.countDown();
    });

    if (!latchForHeartbeat.await(10000, TimeUnit.MILLISECONDS)) {
      System.err.println("failed to register");
      return;
    }

    final var runnable = new ServiceRunnable(webConfig, new WebMetrics(prometheusMetricsFactory), serviceBase, heartbeat.get());
    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
      @Override
      public void run() {
        System.err.println("shutting down frontend");
        runnable.shutdown();
      }
    }));
    System.err.println("running frontend");
    runnable.run();
    System.err.println("frontend finished");
  }

  public static void dashboards() throws Exception {
    PrometheusDashboard metricsFactory = new PrometheusDashboard();
    metricsFactory.page("aws", "AWS");
    new AWSMetrics(metricsFactory);
    metricsFactory.page("gossip", "Gossip");
    new GossipMetricsImpl(metricsFactory);
    metricsFactory.page("client", "Client to Adama");
    new ClientMetrics(metricsFactory);
    metricsFactory.page("server", "Adama Server");
    new ServerMetrics(metricsFactory);
    metricsFactory.page("adama", "Core Service");
    new CoreMetrics(metricsFactory);
    metricsFactory.page("web", "Web Proxy");
    new WebMetrics(metricsFactory);
    metricsFactory.page("overlord", "The Overlord");
    new OverlordMetrics(metricsFactory);
    metricsFactory.page("api", "The Public API");
    new ApiMetrics(metricsFactory);
    metricsFactory.page("backend", "The Data Service");
    new BackendMetrics(metricsFactory);
    metricsFactory.page("database", "Database");
    new DataBaseMetrics(metricsFactory, "frontend");
    new DataBaseMetrics(metricsFactory, "backend");
    new DataBaseMetrics(metricsFactory, "deployed");
    metricsFactory.finish(new File("./prometheus/consoles"));
  }
}
