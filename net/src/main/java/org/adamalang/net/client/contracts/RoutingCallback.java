/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.net.client.contracts;

import org.adamalang.common.ErrorCodeException;

/** what happens when you subscribe to a key */
public interface RoutingCallback {

  /** routing found the document in another region */
  public void onRegion(String region);

  /** routing found the document within the current region on a specific machine */
  public void onMachine(String machine);

  /** a failure in finding a machine */
  public void failure(ErrorCodeException ex);
}
