/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See https://www.adama-platform.com/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.net.client.routing.finder;

import org.adamalang.ErrorCodes;
import org.adamalang.common.Callback;
import org.adamalang.common.ErrorCodeException;
import org.adamalang.net.client.ClientMetrics;
import org.adamalang.net.client.contracts.RoutingCallback;
import org.adamalang.net.client.routing.cache.AggregatedCacheRouter;
import org.adamalang.runtime.data.Key;

public class ReactiveCacheMachinePicker implements MachinePicker {
  private final ClientMetrics metrics;
  private final AggregatedCacheRouter engine;
  private final MachinePicker fallback;

  public ReactiveCacheMachinePicker(ClientMetrics metrics, AggregatedCacheRouter engine, MachinePicker fallback) {
    this.metrics = metrics;
    this.engine = engine;
    this.fallback = fallback;
  }

  @Override
  public void pickHost(Key key, Callback<String> callback) {
    engine.get(key, new RoutingCallback() {
      @Override
      public void onRegion(String region) {
        // impossible for now
        callback.failure(new ErrorCodeException(ErrorCodes.NET_FINDER_ROUTER_REGION_NOT_EXPECTED));
      }

      @Override
      public void failure(ErrorCodeException ex) {
        callback.failure(ex);
      }

      @Override
      public void onMachine(String machine) {
        if (machine == null) {
          metrics.client_rxcache_fallback.run();
          fallback.pickHost(key, callback);
        } else {
          metrics.client_rxcache_found.run();
          callback.success(machine);
        }
      }
    });
  }
}
