/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE'
 * which is in the root directory of the repository. This file is part of the 'Adama'
 * project which is a programming language and document store for board games.
 * 
 * See http://www.adama-lang.org/ for more information.
 * 
 * (c) 2020 - 2021 by Jeffrey M. Barber (http://jeffrey.io)
*/
package org.adamalang.common;

/** time is not a function, so we make it a function */
public interface TimeSource {
  public static final TimeSource REAL_TIME = System::currentTimeMillis;

  /** get the current time */
  public long nowMilliseconds();
}
