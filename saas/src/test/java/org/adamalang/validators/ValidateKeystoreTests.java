/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.validators;

import org.adamalang.common.Json;
import org.adamalang.transforms.results.Keystore;
import org.junit.Test;

public class ValidateKeystoreTests {
  @Test
  public void coverage() throws Exception {
    Keystore keystore = Keystore.parse("{}");
    keystore.generate("AHAAA");
    ValidateKeystore.validate(Json.parseJsonObject(keystore.persist()));
  }
}
