package org.adamalang.common.codec;

import io.netty.buffer.ByteBuf;

import org.adamalang.common.codec.Helper;

import org.adamalang.common.codec.CodecCodeGenTests.TestClassA;
import org.adamalang.common.codec.CodecCodeGenTests.TestClassB;

public class GeneratedCodecMe {
  public static interface Handler {
    public void handle(TestClassA payload);
    public void handle(TestClassB payload);
  }

  public static void route(ByteBuf buf, Handler handler) {
    switch (buf.readIntLE()) {
      case 123:
        handler.handle(readBody_123(buf, new TestClassA()));
        return;
      case 42:
        handler.handle(readBody_42(buf, new TestClassA()));
        return;
      case 4242:
        handler.handle(readBody_4242(buf, new TestClassB()));
        return;
    }
  }

  public static TestClassA read_TestClassA(ByteBuf buf) {
    switch (buf.readIntLE()) {
      case 123:
        return readBody_123(buf, new TestClassA());
      case 42:
        return readBody_42(buf, new TestClassA());
    }
    return null;
  }

  public static TestClassA readRegister_TestClassA(ByteBuf buf, TestClassA o) {
    switch (buf.readIntLE()) {
      case 123:
        return readBody_123(buf, o);
      case 42:
        return readBody_42(buf, o);
    }
    return null;
  }

  private static TestClassA readBody_123(ByteBuf buf, TestClassA o) {
    o.x = buf.readIntLE();
    o.str = Helper.readString(buf);
    o.w = buf.readDoubleLE();
    o.sssshort = buf.readShortLE();
    return o;
  }

  private static TestClassA readBody_42(ByteBuf buf, TestClassA o) {
    o.x = buf.readIntLE();
    o.z = buf.readDoubleLE();
    o.w = buf.readDoubleLE();
    o.sssshort = buf.readShortLE();
    return o;
  }

  public static TestClassB read_TestClassB(ByteBuf buf) {
    switch (buf.readIntLE()) {
      case 4242:
        return readBody_4242(buf, new TestClassB());
    }
    return null;
  }

  public static TestClassB readRegister_TestClassB(ByteBuf buf, TestClassB o) {
    switch (buf.readIntLE()) {
      case 4242:
        return readBody_4242(buf, o);
    }
    return null;
  }

  private static TestClassB readBody_4242(ByteBuf buf, TestClassB o) {
    o.x = buf.readIntLE();
    o.embed = read_TestClassA(buf);
    return o;
  }

  public static void write(ByteBuf buf, TestClassA o) {
    if (o == null) {
      buf.writeIntLE(0);
      return;
    }
    buf.writeIntLE(123);
    buf.writeIntLE(o.x);
    Helper.writeString(buf, o.str);;
    buf.writeDoubleLE(o.w);
    buf.writeShortLE(o.sssshort);
  }

  public static void write(ByteBuf buf, TestClassB o) {
    if (o == null) {
      buf.writeIntLE(0);
      return;
    }
    buf.writeIntLE(4242);
    buf.writeIntLE(o.x);
    write(buf, o.embed);;
  }
}