/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (it's dual licensed) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2021 - 2023 by Adama Platform Initiative, LLC
 */
package org.adamalang.impl.global;

import org.adamalang.ErrorCodes;
import org.adamalang.common.*;
import org.adamalang.frontend.Session;
import org.adamalang.frontend.global.GlobalExternNexus;
import org.adamalang.mysql.DataBase;
import org.adamalang.mysql.data.Domain;
import org.adamalang.mysql.model.Domains;
import org.adamalang.contracts.DomainWithPolicyResolver;
import org.adamalang.contracts.SpacePolicyLocator;
import org.adamalang.contracts.data.DomainWithPolicy;
import org.adamalang.contracts.data.SpacePolicy;

public class GlobalDomainWithPolicyResolver implements DomainWithPolicyResolver {
  private static final ExceptionLogger LOGGER = ExceptionLogger.FOR(DomainWithPolicyResolver.class);
  private final SimpleExecutor executor;
  private final SpacePolicyLocator spacePolicyLocator;
  private final DataBase dataBase;

  public GlobalDomainWithPolicyResolver(SimpleExecutor executor, SpacePolicyLocator spacePolicyLocator, GlobalExternNexus nexus) {
    this.executor = executor;
    this.spacePolicyLocator = spacePolicyLocator;
    this.dataBase = nexus.database;
  }

  public void execute(Session session, String domain, Callback<DomainWithPolicy> callback) {
    executor.execute(new NamedRunnable("resolving-domain") {
      @Override
      public void execute() throws Exception {
        try {
          Domain domainRecord = Domains.get(dataBase, domain);
          if (domainRecord == null) {
            callback.success(new DomainWithPolicy(null, null));
            return;
          }
          if (domainRecord.space == null) {
            callback.success(new DomainWithPolicy(domainRecord, null));
            return;
          }
          spacePolicyLocator.execute(session, domainRecord.space, new Callback<>() {
            @Override
            public void success(SpacePolicy policy) {
              callback.success(new DomainWithPolicy(domainRecord, policy));
            }

            @Override
            public void failure(ErrorCodeException ex) {
              callback.failure(ex);
            }
          });
        } catch (Exception ex) {
          callback.failure(ErrorCodeException.detectOrWrap(ErrorCodes.DOMAIN_RESOLVE_UNKNOWN_EXCEPTION, ex, LOGGER));
        }
      }
    });
  }

}