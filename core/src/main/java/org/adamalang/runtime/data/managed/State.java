/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.runtime.data.managed;

public enum State {
  // we don't know the state
  Unknown,

  // an outbound find request has been requested
  Finding,

  // the state is on the machine without any updates
  OnMachine,

  // the state is in the archive and is being restored
  Restoring
}
