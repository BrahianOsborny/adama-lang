package org.adamalang.canary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {
  public final AtomicInteger deltas;
  public final AtomicInteger messages_sent;
  public final AtomicInteger messages_acked;
  public final AtomicInteger messages_failed;
  public final AtomicInteger stream_failed;
  private final ArrayList<Integer> connect_latency;
  private final ArrayList<Integer> send_latency;
  private final HashMap<Integer, Integer> failure_reasons;

  public Metrics() {
    this.deltas = new AtomicInteger(0);
    this.messages_sent = new AtomicInteger(0);
    this.messages_acked = new AtomicInteger(0);
    this.messages_failed = new AtomicInteger(0);
    this.stream_failed = new AtomicInteger(0);
    this.failure_reasons = new HashMap();
    this.connect_latency = new ArrayList<>();
    this.send_latency = new ArrayList<>();
  }

  public synchronized void record_connect_latency(int x) {
    connect_latency.add(x);
  }

  public synchronized void record_send_latency(int x) {
    send_latency.add(x);
  }

  public synchronized void report_failure(int code) {
    Integer prior = failure_reasons.get(code);
    if (prior == null) {
      failure_reasons.put(code, 1);
    } else {
      failure_reasons.put(code, prior + 1);
    }
  }

  public synchronized void snapshot() {
    StringBuilder sb = new StringBuilder();
    boolean append = false;
    for (Map.Entry<Integer, Integer> entry : failure_reasons.entrySet()) {
      if (append) {
        sb.append("|");
      }
      append = true;
      sb.append(entry.getKey() + "=" + entry.getValue());
    }
    int p95Latency = 0;
    if (send_latency.size() > 5) {
      send_latency.sort(Integer::compare);
      p95Latency = send_latency.get((int) (send_latency.size() * 0.95));
    }
    System.out.println(deltas.get() + "," + messages_sent.get() + "," + messages_acked.get() + "," + messages_failed.get() + "," + stream_failed.get() + "," + p95Latency + "," + sb.toString());
  }
}
