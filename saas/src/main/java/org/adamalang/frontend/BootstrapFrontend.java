/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.frontend;

import org.adamalang.api.ConnectionNexus;
import org.adamalang.api.ConnectionRouter;
import org.adamalang.common.SimpleExecutor;
import org.adamalang.common.SimpleExecutorFactory;
import org.adamalang.connection.Session;
import org.adamalang.extern.ExternNexus;
import org.adamalang.transforms.PerSessionAuthenticator;
import org.adamalang.transforms.SpacePolicyLocator;
import org.adamalang.transforms.UserIdResolver;
import org.adamalang.web.assets.AssetSystem;
import org.adamalang.web.contracts.*;
import org.adamalang.web.io.ConnectionContext;
import org.adamalang.web.io.JsonRequest;
import org.adamalang.web.io.JsonResponder;

import java.util.Random;

public class BootstrapFrontend {
  public static ServiceBase make(ExternNexus extern, HttpHandler httpHandler) throws Exception {
    SimpleExecutor[] executors = SimpleExecutorFactory.DEFAULT.makeMany("saas", extern.config.threads);
    RootHandlerImpl handler = new RootHandlerImpl(extern);
    SpacePolicyLocator spacePolicyLocator = new SpacePolicyLocator(SimpleExecutor.create("space-policy-locator"), extern);
    UserIdResolver userIdResolver = new UserIdResolver(SimpleExecutor.create("user-id-resolver"), extern);

    Random randomExecutorIndex = new Random();
    return new ServiceBase() {
      @Override
      public ServiceConnection establish(ConnectionContext context) {
        return new ServiceConnection() {
          final Session session = new Session(new PerSessionAuthenticator(extern.database, context, extern.superPublicKeys));
          final ConnectionNexus nexus =
              new ConnectionNexus(extern.accessLogger, //
                  extern.metrics, //
                  executors[randomExecutorIndex.nextInt(executors.length)], //
                  userIdResolver, //
                  session.authenticator, //
                  spacePolicyLocator); //
          final ConnectionRouter router = new ConnectionRouter(session, nexus, handler);

          @Override
          public void execute(JsonRequest request, JsonResponder responder) {
            router.route(request, responder);
          }

          @Override
          public boolean keepalive() {
            return session.keepalive();
          }

          @Override
          public void kill() {
            router.disconnect();
          }
        };
      }

      @Override
      public HttpHandler http() {
        return httpHandler;
      }

      @Override
      public AssetSystem assets() {
        return extern.assets;
      }
    };
  }
}
