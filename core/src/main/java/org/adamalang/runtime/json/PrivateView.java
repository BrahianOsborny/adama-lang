/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See http://www.adama-lang.org/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber (http://jeffrey.io)
 */
package org.adamalang.runtime.json;

import org.adamalang.runtime.contracts.Perspective;
import org.adamalang.runtime.natives.NtClient;

/** a private view of the document where private lives; code is generated to use this */
public abstract class PrivateView {
  private static final String DEFAULT_FUTURES = "\"outstanding\":[],\"blockers\":[]";
  public final Perspective perspective;
  public final NtClient who;
  private boolean alive;
  private PrivateView usurper;
  private String lastWrittenFutures;

  /** construct the view based on the person (who) and the connection (perspective) */
  public PrivateView(final NtClient who, final Perspective perspective) {
    alive = true;
    this.who = who;
    this.perspective = perspective;
    this.lastWrittenFutures = DEFAULT_FUTURES;
  }

  /**
   * a new private view was created on a different document which is usurping the existing document.
   * Since private views leak outside the document, this creates a proxy link for the client to kill
   * both views.
   */
  public void usurp(PrivateView usurper) {
    this.usurper = usurper;
  }

  /** codegen: seed the state held by the view */
  public abstract void ingest(JsonStreamReader reader);

  /** codegen: dump the data held by the view */
  public abstract void dumpViewer(JsonStreamWriter writer);

  /** send the user a delivery update */
  public void deliver(final String delivery) {
    perspective.data(delivery);
  }

  /** is the view still alive and interesting to the user */
  public synchronized boolean isAlive() {
    return alive;
  }

  /** dedupe excessive outstanding and blockers sharing */
  public boolean futures(String futures) {
    String futuresToTest = futures;
    if (futuresToTest.equals(DEFAULT_FUTURES)) { // save some memory
      futuresToTest = DEFAULT_FUTURES;
    }
    if (futuresToTest == lastWrittenFutures || lastWrittenFutures.equals(futures)) {
      return false;
    } else {
      lastWrittenFutures = futures;
      return true;
    }
  }

  /** the client is no longer interested */
  public synchronized void kill() {
    if (usurper != null) {
      // this has been usurped, so kill the usurper as well
      usurper.kill();
    }
    alive = false;
  }

  /** codegen: the data inside the view has been updated, stream it out to the writer */
  public abstract void update(JsonStreamWriter writer);
}
