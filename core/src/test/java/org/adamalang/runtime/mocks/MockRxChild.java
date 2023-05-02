/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.runtime.mocks;

import org.adamalang.runtime.contracts.RxChild;
import org.junit.Assert;

public class MockRxChild implements RxChild {
  public boolean alive;
  public int invalidCount;

  public MockRxChild() {
    invalidCount = 0;
    alive = true;
  }

  @Override
  public boolean __raiseInvalid() {
    invalidCount++;
    return alive;
  }

  public void assertInvalidateCount(final int expected) {
    Assert.assertEquals(expected, invalidCount);
  }
}
