/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE'
 * which is in the root directory of the repository. This file is part of the 'Adama'
 * project which is a programming language and document store for board games.
 * 
 * See http://www.adama-lang.org/ for more information.
 * 
 * (c) 2020 - 2021 by Jeffrey M. Barber (http://jeffrey.io)
*/
package org.adamalang.api.commands;

import org.adamalang.api.util.Json;
import org.adamalang.runtime.exceptions.ErrorCodeException;
import org.junit.Assert;
import org.junit.Test;

public class RequestTests {
  @Test
  public void present() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"method\":\"foo\",\"space\":\"game\",\"id\":42,\"key\":\"10000\",\"entropy\":\"jazz\",\"marker\":\"mmm\",\"channel\":\"ch\",\"message\":{},\"arg\":{}}"));
    Assert.assertEquals("foo", request.method());
    Assert.assertEquals("game", request.space());
    Assert.assertEquals("mmm", request.marker());
    Assert.assertEquals("ch", request.channel());
    Assert.assertEquals("jazz", request.entropy());
    Assert.assertEquals(42, request.id());
    Assert.assertEquals("10000", request.key());
    Assert.assertEquals("{}", request.json_arg());
    Assert.assertEquals("{}", request.json_message());
  }

  @Test
  public void lng_as_ints() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"key\":10000}"));
    Assert.assertEquals("10000", request.key());
  }

  @Test
  public void lng_inv() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"key\":true}"));
    try {
      request.key();
      Assert.fail();
    } catch (ErrorCodeException ece) {
    }
  }

  @Test
  public void json_invalid() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"message\":\"x\",\"arg\":[]}"));
    try {
      request.json_message();
      Assert.fail();
    } catch (ErrorCodeException ece) {
    }
    try {
      request.json_arg();
      Assert.fail();
    } catch (ErrorCodeException ece) {
    }
  }

  @Test
  public void missing() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{}"));
    try {
      request.method();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40102, ece.code);
    }
    try {
      request.space();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40100, ece.code);
    }
    try {
      request.marker();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40107, ece.code);
    }
    try {
      request.channel();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40104, ece.code);
    }
    Assert.assertNull(request.entropy());
    try {
      request.key();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40101, ece.code);
    }
    try {
      request.id();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40110, ece.code);
    }
  }

  @Test
  public void missing_impersonate1() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"impersonate\":{}}"));
    try {
      request.impersonate();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40111, ece.code);
    }
  }

  @Test
  public void missing_impersonate2() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"impersonate\":{\"agent\":\"f\"}}"));
    try {
      request.impersonate();
      Assert.fail();
    } catch (ErrorCodeException ece) {
      Assert.assertEquals(40112, ece.code);
    }
  }

  @Test
  public void happy_impersonate() throws ErrorCodeException {
    Request request = new Request(Json.parseJsonObject("{\"impersonate\":{\"agent\":\"f\",\"authority\":\"a\"}}"));
    Assert.assertEquals("f", request.impersonate().agent);
    Assert.assertEquals("a", request.impersonate().authority);
  }

}
