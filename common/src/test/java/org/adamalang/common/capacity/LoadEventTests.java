/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.common.capacity;

import org.junit.Assert;
import org.junit.Test;

public class LoadEventTests {
  @Test
  public void flow() {
    StringBuilder sb = new StringBuilder();
    LoadEvent le = new LoadEvent(0.5, (b) -> sb.append(b ? "START": "STOP"));
    le.at(.4);
    le.at(.4);
    le.at(.4);
    le.at(.6);
    le.at(.6);
    le.at(.6);
    le.at(.4);
    le.at(.4);
    le.at(.4);
    le.at(.6);
    le.at(.4);
    Assert.assertEquals("STARTSTOPSTARTSTOP", sb.toString());
  }
}
