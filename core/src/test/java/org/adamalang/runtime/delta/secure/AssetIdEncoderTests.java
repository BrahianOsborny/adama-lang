/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.runtime.delta.secure;

import org.junit.Assert;
import org.junit.Test;

import javax.crypto.SecretKey;

public class AssetIdEncoderTests {
  @Test
  public void flow() throws Exception {
    String key = SecureAssetUtil.makeAssetKeyHeader();
    AssetIdEncoder encoder = new AssetIdEncoder(key);
    String enc = encoder.encrypt("1234");
    SecretKey pkey = SecureAssetUtil.secretKeyOf(key);
    Assert.assertEquals("1234", SecureAssetUtil.decryptFromBase64(pkey, enc));
  }
}
