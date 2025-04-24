package com.sbs.java.board.global.base;

import com.sbs.java.board.global.util.Util;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

public class Rq {
  public String url;

  @Getter
  public Map<String, String> params;
  @Getter
  public String urlPath;

  @Setter
  @Getter
  String controllerTypeCode;
  @Setter
  @Getter
  String controllerName;
  @Setter
  @Getter
  String actionMethodName;

  public Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getPathFromUrl(this.url);
  }

  public String getActionPath() {
    String[] commandBits = urlPath.split("/");

    controllerTypeCode = commandBits[1];
    controllerName = commandBits[2];
    actionMethodName = commandBits[3];

    return "/%s/%s/%s".formatted(controllerTypeCode, controllerName, actionMethodName);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if (!params.containsKey(paramName)) return defaultValue;

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if (!params.containsKey(paramName)) return defaultValue;

    return params.get(paramName);
  }
}