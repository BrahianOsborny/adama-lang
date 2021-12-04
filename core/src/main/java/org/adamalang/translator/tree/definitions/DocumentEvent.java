/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE'
 * which is in the root directory of the repository. This file is part of the 'Adama'
 * project which is a programming language and document store for board games.
 * 
 * See http://www.adama-lang.org/ for more information.
 * 
 * (c) 2020 - 2021 by Jeffrey M. Barber (http://jeffrey.io)
*/
package org.adamalang.translator.tree.definitions;

/** an event that happens outside of message flow */
public enum DocumentEvent {
  AskCreation, //
  AskAssetAttachment, //
  AssetAttachment, //
  ClientConnected, //
  ClientDisconnected //
}
