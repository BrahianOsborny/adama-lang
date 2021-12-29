/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.mysql.mocks;

import org.adamalang.common.ErrorCodeException;
import org.adamalang.runtime.contracts.ActiveKeyStream;
import org.adamalang.runtime.contracts.Key;
import org.junit.Assert;

import java.util.HashMap;

public class MockActiveKeyStream implements ActiveKeyStream {
  public final HashMap<Key, Long> schedule;
  public boolean finished;
  public boolean crashFinish;
  ErrorCodeException failure;

  public MockActiveKeyStream() {
    schedule = new HashMap<>();
    finished = false;
    failure = null;
    crashFinish = false;
  }

  @Override
  public void schedule(Key key, long time) {
    this.schedule.put(key, time);
  }

  @Override
  public void finish() {
    if (crashFinish) {
      throw new NullPointerException();
    }
    finished = true;
  }

  @Override
  public void error(ErrorCodeException failure) {
    this.failure = failure;
  }

  public void assertFinished(int count) {
    Assert.assertTrue(finished);
    Assert.assertEquals(count, schedule.size());
  }

  public void assertHas(Key key) {
    Assert.assertTrue(schedule.containsKey(key));
  }

  public void assertFailure(int code) {
    Assert.assertNotNull(failure);
    Assert.assertEquals(code, failure.code);
  }
}
