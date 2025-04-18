package com.sbs.java.board;

import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    // 디스패처 서블릿 방식
    App app = new App();
    app.run(); // 로직을 실행
  } 
}

class Article {
  int id;
  String subject;
  String content;

  Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();

    String[] urlBits = url.split("\\?", 2);

    if (urlBits.length == 1) return params;

    String queryStr = urlBits[1];

    for (String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if (bits.length == 1) continue;

      params.put(bits[0], bits[1]);
    }

    return params;
  }

  static String getPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }
}