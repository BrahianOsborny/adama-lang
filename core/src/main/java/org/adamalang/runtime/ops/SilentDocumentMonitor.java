/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.runtime.ops;

import org.adamalang.runtime.contracts.DocumentMonitor;

/** a monitor which does nothing */
public class SilentDocumentMonitor implements DocumentMonitor {
  @Override
  public void assertFailureAt(final int startLine, final int startPosition, final int endLine, final int endLinePosition, final int total, final int failures) {
  }

  @Override
  public void goodwillFailureAt(final int startLine, final int startPosition, final int endLine, final int endLinePosition) {
  }

  @Override
  public void pop(final long time, final boolean exception) {
  }

  @Override
  public void push(final String label) {
  }

  @Override
  public void registerTableColumnIndexEffectiveness(final String tableName, final String colummName, final int total, final int effectiveness) {
  }

  @Override
  public boolean shouldMeasureTableColumnIndexEffectiveness() {
    return false;
  }
}
