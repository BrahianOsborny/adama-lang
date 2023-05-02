/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.translator.jvm;

import org.junit.Assert;
import org.junit.Test;

import java.util.TreeMap;

public class ByteArrayClassLoaderTests {
  @Test
  public void coverage() throws Exception {
    final var bacl = new ByteArrayClassLoader(new TreeMap<>());
    try {
      bacl.findClass("Ninja");
      Assert.fail();
    } catch (final ClassNotFoundException cnfe) {
      Assert.assertEquals("Ninja", cnfe.getMessage());
    }
  }
}
