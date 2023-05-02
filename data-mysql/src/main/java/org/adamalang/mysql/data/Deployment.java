/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (hint: it's MIT-based) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2020 - 2023 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.mysql.data;

public class Deployment {
  public final String space;
  public final String hash;
  public final String plan;
  public final String target;

  public Deployment(String space, String hash, String plan, String target) {
    this.space = space;
    this.hash = hash;
    this.plan = plan;
    this.target = target;
  }
}
