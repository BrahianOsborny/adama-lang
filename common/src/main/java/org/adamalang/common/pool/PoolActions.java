/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.common.pool;

import org.adamalang.common.Callback;

/** The pool is asynchronous and has to make external calls to create and kill items within the pool */
public interface PoolActions<R, S> {
  /** request an item to be created to be placed within the pool */
  public void create(R request, Callback<S> created);

  /** destroy an item as it is leaving the pool */
  public void destroy(S item);
}
