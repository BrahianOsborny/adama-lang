/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.translator.tree.types.traits;

import org.adamalang.translator.env.Environment;
import org.adamalang.translator.tree.types.TyType;

public interface IsMap {
  TyType getDomainType(Environment environment);

  TyType getRangeType(Environment environment);
}
