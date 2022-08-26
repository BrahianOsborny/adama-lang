/*
 * This file is subject to the terms and conditions outlined in the file 'LICENSE' (hint: it's MIT); this file is located in the root directory near the README.md which you should also read.
 *
 * This file is part of the 'Adama' project which is a programming language and document store for board games; however, it can be so much more.
 *
 * See https://www.adama-platform.com/ for more information.
 *
 * (c) 2020 - 2022 by Jeffrey M. Barber ( http://jeffrey.io )
 */
package org.adamalang.rxhtml;

public class TemplateStyleAggrTests extends BaseRxHtmlTest {
  @Override
  public String issues() {
    StringBuilder issues = new StringBuilder();
    issues.append("");
    return issues.toString();
  }
  @Override
  public String gold() {
    StringBuilder gold = new StringBuilder();
    gold.append("JavaScript:(function($){");
    gold.append("\n  $.PG(['fixed',''], function(b,a) {");
    gold.append("\n    var c = $.E('button');");
    gold.append("\n    $.onT(c,'click',$.pV(a),'open');");
    gold.append("\n    c.append($.T('Toggle'));");
    gold.append("\n    b.append(c);");
    gold.append("\n    var c = $.E('button');");
    gold.append("\n    $.onT(c,'mouseenter',$.pV(a),'enter');");
    gold.append("\n    c.append($.T('Toggle'));");
    gold.append("\n    b.append(c);");
    gold.append("\n  });");
    gold.append("\n})(RxHTML);");
    gold.append("\nStyle:common style here also common also common");
    gold.append("\nShell:<!DOCTYPE html>");
    gold.append("\n<html>");
    gold.append("\n<head><script src=\"https://aws-us-east-2.adama-platform.com/libadama.js\"></script><script src=\"https://aws-us-east-2.adama-platform.com/rxhtml.js\"></script><link rel=\"stylesheet\" href=\"/template.css\"><script src=\"/template.js\"></script></head><body></body><script>RxHTML.init();</script></html>");
    return gold.toString();
  }
  @Override
  public String source() {
    StringBuilder source = new StringBuilder();
    source.append("<forest>");
    source.append("\n    <style>");
    source.append("\n        common style here");
    source.append("\n    </style>");
    source.append("\n    <page uri=\"/\">");
    source.append("\n        <style>");
    source.append("\n            also common");
    source.append("\n        </style>");
    source.append("\n        <button rx:click=\"toggle:open\">Toggle</button>");
    source.append("\n        <button rx:mouseenter=\"toggle:enter\">Toggle</button>");
    source.append("\n        <style>");
    source.append("\n            also common");
    source.append("\n        </style>");
    source.append("\n    </page>");
    source.append("\n</forest>");
    return source.toString();
  }
}