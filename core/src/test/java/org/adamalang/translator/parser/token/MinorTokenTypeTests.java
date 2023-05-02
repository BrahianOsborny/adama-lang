/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.translator.parser.token;

import org.junit.Assert;
import org.junit.Test;

public class MinorTokenTypeTests {
  @Test
  public void coverage() {
    for (final MinorTokenType mtt : MinorTokenType.values()) {
      Assert.assertEquals(mtt, MinorTokenType.valueOf(mtt.name()));
    }
  }
}
