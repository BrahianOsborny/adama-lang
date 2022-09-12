/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See https://www.adama-platform.com/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.mysql.model;

import org.adamalang.common.metrics.NoOpMetricsFactory;
import org.adamalang.mysql.*;
import org.adamalang.mysql.data.Domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DomainsTest {
  @Test
  public void flow() throws Exception {
    DataBaseConfig dataBaseConfig = DataBaseConfigTests.getLocalIntegrationConfig();
    try (DataBase dataBase = new DataBase(dataBaseConfig, new DataBaseMetrics(new NoOpMetricsFactory()))) {
      Installer installer = new Installer(dataBase);
      try {
        installer.install();
        Domain result = Domains.get(dataBase, "www.domain.com");
        Assert.assertNull(result);
        Assert.assertTrue(Domains.map(dataBase, 141, "www.domain.com", "my-space", "certificate-yay"));
        result = Domains.get(dataBase, "www.domain.com");
        Assert.assertEquals("my-space", result.space);
        Assert.assertEquals("certificate-yay", result.certificate);
        Assert.assertEquals(141, result.owner);
        Assert.assertFalse(Domains.map(dataBase, 145, "www.domain.com", "my", "yo"));
        result = Domains.get(dataBase, "www.domain.com");
        Assert.assertEquals("my-space", result.space);
        Assert.assertEquals("certificate-yay", result.certificate);
        Assert.assertEquals(141, result.owner);
        Assert.assertTrue(Domains.map(dataBase, 141, "www.domain.com", "my-space-two", "certificate-ok"));
        Assert.assertFalse(Domains.unmap(dataBase, 150, "www.domain.com"));
        result = Domains.get(dataBase, "www.domain.com");
        Assert.assertEquals("my-space-two", result.space);
        Assert.assertEquals("certificate-ok", result.certificate);
        Assert.assertEquals(141, result.owner);
        Assert.assertTrue(Domains.unmap(dataBase, 141, "www.domain.com"));
        result = Domains.get(dataBase, "www.domain.com");
        Assert.assertNull(result);
        Assert.assertTrue(Domains.map(dataBase, 42, "www.domain1.com", "space", "certificate-ok"));
        Assert.assertTrue(Domains.map(dataBase, 42, "www.domain2.com", "space", "certificate-ok"));
        Assert.assertTrue(Domains.map(dataBase, 42, "www.domain3.com", "space", "certificate-ok"));
        Assert.assertEquals(3, Domains.deleteSpace(dataBase, "space"));
        Assert.assertTrue(Domains.map(dataBase, 500, "www.my-domain.com", "space", null));
        Assert.assertTrue(Domains.map(dataBase, 500, "www.my-domain.com", "space2", null));
        ArrayList<Domain> superList = Domains.superListAutoDomains(dataBase);
        Assert.assertEquals("space2", superList.get(0).space);
        Assert.assertNull(superList.get(0).certificate);
        Assert.assertTrue(Domains.superSetAutoCert(dataBase, "www.my-domain.com", "new-cert"));
        superList = Domains.superListAutoDomains(dataBase);
        Assert.assertEquals("new-cert", superList.get(0).certificate);
      } finally {
        installer.uninstall();
      }
    }
  }
}