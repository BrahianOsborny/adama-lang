/*
 * This file is subject to the terms and conditions outlined in the
 * file 'LICENSE' (it's dual licensed) located in the root directory
 * near the README.md which you should also read. For more information
 * about the project which owns this file, see https://www.adama-platform.com/ .
 *
 * (c) 2021 - 2023 by Adama Platform Initiative, LLC
 */
package org.adamalang;

import org.adamalang.control.*;
import org.adamalang.mysql.DataBase;

public class ControlPlaneHandler implements RootHandler {
  private final DataBase database;
  private final ControlPlaneExecutor executor;

  private ControlPlaneHandler(DataBase dataBase, ControlPlaneExecutor executor) {
    this.database = dataBase;
    this.executor = executor;
  }

  @Override
  public void handle(Session session, GlobalMachineStartRequest request, MachineStartResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderFindRequest request, FoundResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderFindbindRequest request, FoundResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderFreeRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderDeleteMarkRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderDeleteCommitRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderBackUpRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalFinderListRequest request, KeyidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesCreateRequest request, AuthorityResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesSetRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesGetPublicRequest request, KeystoreResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesGetProtectedRequest request, KeystoreResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesListRequest request, AuthorityListResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void handle(Session session, GlobalAuthoritiesDeleteRequest request, VoidResponder responder) {
    executor.execute(() -> {

    }, session, responder.responder);
  }

  @Override
  public void disconnect() {

  }
}