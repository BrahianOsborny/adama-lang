/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See https://www.adama-platform.com/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.adamalang.TestFrontEnd;
import org.adamalang.common.Json;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

public class GeneratedMissingParameterTest {
  @Test
  public void missing() throws Exception {
    try (TestFrontEnd fe = new TestFrontEnd()) {
      ObjectNode node;
      String _identity = fe.setupDevIdentity();
      //InitSetupAccount
      node = Json.newJsonObject();
      node.put("id", 1);
      node.put("method", "init/setup-account");
      Iterator<String> c2 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:473103", c2.next());
      node.put("email", "x@x.com");
      //InitCompleteAccount
      node = Json.newJsonObject();
      node.put("id", 2);
      node.put("method", "init/complete-account");
      Iterator<String> c3 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:473103", c3.next());
      node.put("email", "x@x.com");
      Iterator<String> c4 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:455681", c4.next());
      node.put("code", "xzya");
      //AccountSetPassword
      node = Json.newJsonObject();
      node.put("id", 4);
      node.put("method", "account/set-password");
      Iterator<String> c5 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c5.next());
      node.put("identity", _identity);
      Iterator<String> c6 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:465917", c6.next());
      node.put("password", "xzya");
      //AccountLogin
      node = Json.newJsonObject();
      node.put("id", 6);
      node.put("method", "account/login");
      Iterator<String> c7 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:473103", c7.next());
      node.put("email", "x@x.com");
      Iterator<String> c8 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:465917", c8.next());
      node.put("password", "xzya");
      //Probe
      node = Json.newJsonObject();
      node.put("id", 8);
      node.put("method", "probe");
      Iterator<String> c9 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c9.next());
      node.put("identity", _identity);
      //AuthorityCreate
      node = Json.newJsonObject();
      node.put("id", 9);
      node.put("method", "authority/create");
      Iterator<String> c10 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c10.next());
      node.put("identity", _identity);
      //AuthoritySet
      node = Json.newJsonObject();
      node.put("id", 10);
      node.put("method", "authority/set");
      Iterator<String> c11 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c11.next());
      node.put("identity", _identity);
      Iterator<String> c12 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:430095", c12.next());
      node.put("authority", "xzya");
      Iterator<String> c13 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:457743", c13.next());
      node.put("key-store", Json.newJsonObject());
      //AuthorityGet
      node = Json.newJsonObject();
      node.put("id", 13);
      node.put("method", "authority/get");
      Iterator<String> c14 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c14.next());
      node.put("identity", _identity);
      Iterator<String> c15 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:430095", c15.next());
      node.put("authority", "xzya");
      //AuthorityList
      node = Json.newJsonObject();
      node.put("id", 15);
      node.put("method", "authority/list");
      Iterator<String> c16 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c16.next());
      node.put("identity", _identity);
      //AuthorityDestroy
      node = Json.newJsonObject();
      node.put("id", 16);
      node.put("method", "authority/destroy");
      Iterator<String> c17 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c17.next());
      node.put("identity", _identity);
      Iterator<String> c18 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:430095", c18.next());
      node.put("authority", "xzya");
      //SpaceCreate
      node = Json.newJsonObject();
      node.put("id", 18);
      node.put("method", "space/create");
      Iterator<String> c19 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c19.next());
      node.put("identity", _identity);
      Iterator<String> c20 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c20.next());
      node.put("space", "xzya");
      //SpaceGenerateKey
      node = Json.newJsonObject();
      node.put("id", 20);
      node.put("method", "space/generate-key");
      Iterator<String> c21 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c21.next());
      node.put("identity", _identity);
      Iterator<String> c22 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c22.next());
      node.put("space", "xzya");
      //SpaceUsage
      node = Json.newJsonObject();
      node.put("id", 22);
      node.put("method", "space/usage");
      Iterator<String> c23 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c23.next());
      node.put("identity", _identity);
      Iterator<String> c24 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c24.next());
      node.put("space", "xzya");
      //SpaceGet
      node = Json.newJsonObject();
      node.put("id", 24);
      node.put("method", "space/get");
      Iterator<String> c25 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c25.next());
      node.put("identity", _identity);
      Iterator<String> c26 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c26.next());
      node.put("space", "xzya");
      //SpaceSet
      node = Json.newJsonObject();
      node.put("id", 26);
      node.put("method", "space/set");
      Iterator<String> c27 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c27.next());
      node.put("identity", _identity);
      Iterator<String> c28 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c28.next());
      node.put("space", "xzya");
      Iterator<String> c29 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:425999", c29.next());
      node.put("plan", Json.newJsonObject());
      //SpaceDelete
      node = Json.newJsonObject();
      node.put("id", 29);
      node.put("method", "space/delete");
      Iterator<String> c30 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c30.next());
      node.put("identity", _identity);
      Iterator<String> c31 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c31.next());
      node.put("space", "xzya");
      //SpaceSetRole
      node = Json.newJsonObject();
      node.put("id", 31);
      node.put("method", "space/set-role");
      Iterator<String> c32 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c32.next());
      node.put("identity", _identity);
      Iterator<String> c33 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c33.next());
      node.put("space", "xzya");
      Iterator<String> c34 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:473103", c34.next());
      node.put("email", "x@x.com");
      Iterator<String> c35 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:456716", c35.next());
      node.put("role", "xzya");
      //SpaceReflect
      node = Json.newJsonObject();
      node.put("id", 35);
      node.put("method", "space/reflect");
      Iterator<String> c36 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c36.next());
      node.put("identity", _identity);
      Iterator<String> c37 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c37.next());
      node.put("space", "xzya");
      Iterator<String> c38 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:466947", c38.next());
      node.put("key", "xzya");
      //SpaceList
      node = Json.newJsonObject();
      node.put("id", 38);
      node.put("method", "space/list");
      Iterator<String> c39 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c39.next());
      node.put("identity", _identity);
      //DocumentCreate
      node = Json.newJsonObject();
      node.put("id", 39);
      node.put("method", "document/create");
      Iterator<String> c40 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c40.next());
      node.put("identity", _identity);
      Iterator<String> c41 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c41.next());
      node.put("space", "xzya");
      Iterator<String> c42 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:466947", c42.next());
      node.put("key", "xzya");
      Iterator<String> c43 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461826", c43.next());
      node.put("arg", Json.newJsonObject());
      //DocumentList
      node = Json.newJsonObject();
      node.put("id", 43);
      node.put("method", "document/list");
      Iterator<String> c44 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c44.next());
      node.put("identity", _identity);
      Iterator<String> c45 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c45.next());
      node.put("space", "xzya");
      //ConnectionCreate
      node = Json.newJsonObject();
      node.put("id", 45);
      node.put("method", "connection/create");
      Iterator<String> c46 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c46.next());
      node.put("identity", _identity);
      Iterator<String> c47 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c47.next());
      node.put("space", "xzya");
      Iterator<String> c48 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:466947", c48.next());
      node.put("key", "xzya");
      //ConnectionSend
      node = Json.newJsonObject();
      node.put("id", 48);
      node.put("method", "connection/send");
      Iterator<String> c49 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:405505", c49.next());
      node.put("connection", 100L);
      Iterator<String> c50 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:454659", c50.next());
      node.put("channel", "xzya");
      Iterator<String> c51 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:425987", c51.next());
      node.put("message", Json.newJsonObject());
      //ConnectionUpdate
      node = Json.newJsonObject();
      node.put("id", 51);
      node.put("method", "connection/update");
      Iterator<String> c52 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:405505", c52.next());
      node.put("connection", 100L);
      //ConnectionEnd
      node = Json.newJsonObject();
      node.put("id", 52);
      node.put("method", "connection/end");
      Iterator<String> c53 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:405505", c53.next());
      node.put("connection", 100L);
      //ConfigureMakeOrGetAssetKey
      node = Json.newJsonObject();
      node.put("id", 53);
      node.put("method", "configure/make-or-get-asset-key");
      //AttachmentStart
      node = Json.newJsonObject();
      node.put("id", 53);
      node.put("method", "attachment/start");
      Iterator<String> c54 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:458759", c54.next());
      node.put("identity", _identity);
      Iterator<String> c55 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:461828", c55.next());
      node.put("space", "xzya");
      Iterator<String> c56 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:466947", c56.next());
      node.put("key", "xzya");
      Iterator<String> c57 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:470028", c57.next());
      node.put("filename", "xzya");
      Iterator<String> c58 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:455691", c58.next());
      node.put("content-type", "xzya");
      //AttachmentAppend
      node = Json.newJsonObject();
      node.put("id", 58);
      node.put("method", "attachment/append");
      Iterator<String> c59 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:409609", c59.next());
      node.put("upload", 100L);
      Iterator<String> c60 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:462859", c60.next());
      node.put("chunk-md5", "xzya");
      Iterator<String> c61 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:409608", c61.next());
      node.put("base64-bytes", "xzya");
      //AttachmentFinish
      node = Json.newJsonObject();
      node.put("id", 61);
      node.put("method", "attachment/finish");
      Iterator<String> c62 = fe.execute(node.toString());
      Assert.assertEquals("ERROR:409609", c62.next());
      node.put("upload", 100L);
    }
  }
}
