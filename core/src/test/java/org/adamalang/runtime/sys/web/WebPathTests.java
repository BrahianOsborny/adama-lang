/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See https://www.adama-platform.com/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.runtime.sys.web;

import org.junit.Assert;
import org.junit.Test;

public class WebPathTests {
  @Test
  public void flow() {
    WebPath router = new WebPath("/xyz/kstar/123");
    Assert.assertEquals("xyz", router.at(0).fragment);
    Assert.assertEquals("kstar", router.at(1).fragment);
    Assert.assertEquals("123", router.at(2).fragment);
    Assert.assertNull(router.at(5));
  }

  @Test
  public void root() {
    WebPath router = new WebPath("/");
    Assert.assertEquals("", router.at(0).fragment);
    Assert.assertNull(router.at(1));
  }

  @Test
  public void bigtail() {
    WebPath router = new WebPath("/xyz/123/wtf");
    Assert.assertEquals("xyz", router.at(0).fragment);
    Assert.assertEquals("xyz/123/wtf", router.at(0).tail());
    Assert.assertEquals("123", router.at(1).fragment);
    Assert.assertEquals("123/wtf", router.at(1).tail());
    Assert.assertEquals("wtf", router.at(2).fragment);
    Assert.assertEquals("wtf", router.at(2).tail());
  }
}