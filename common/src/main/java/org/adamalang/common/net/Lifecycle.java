/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.common.net;

import org.adamalang.common.ErrorCodeException;

/** each connection has a separate lifecycle from birth to death */
public interface Lifecycle {
  /** the connection was created and achieved glorious success */
  void connected(ChannelClient channel);

  /** the connection failed to connect */
  void failed(ErrorCodeException ex);

  /** the successful connection was later disconnected */
  void disconnected();
}
