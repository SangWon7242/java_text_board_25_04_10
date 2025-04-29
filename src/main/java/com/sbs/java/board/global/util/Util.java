package com.sbs.java.board.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Util {
  public static Map<String, String> getParamsFromUrl(String url) {
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

  public static String getPathFromUrl(String url) {
    return url.split("\\?", 2)[0];
  }

  public static String getNowDateStr() {
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");

    String dateStr = now.format(formatter);

    return dateStr;
  }
}
