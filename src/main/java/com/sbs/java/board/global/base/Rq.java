package com.sbs.java.board.global.base;

import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.session.Session;
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

  @Getter
  public Session session;

  public String loginedMember = "loginedMember";

  @Setter
  @Getter
  String controllerTypeCode;
  @Setter
  @Getter
  String controllerName;
  @Setter
  @Getter
  String actionMethodName;

  public Rq() {
    session = Container.session;
  }

  public void setCommand(String url) {
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
  
  // 세션에 로그인 되어 있는지 확인
  public boolean isLogined() {
    return hasSessionAttr(loginedMember);
  }

  // 세션에 로그아웃 되어 있는지 확인
  public boolean isLogout() {
    return !isLogined();
  }

  // 세션 가져오기
  public Object getSessionAttr(String attrName) {
    return session.getAttribute(attrName);
  }

  // 세션 저장
  public void setSessionAttr(String attrName, Object value) {
    session.setAttribute(attrName, value);
  }

  // 세션 존재 여부
  public boolean hasSessionAttr(String attrName) {
    return session.hasAttribute(attrName);
  }

  // 세션 제거
  public void removeSessionAttr(String attrName) {
    session.removeAttribute(attrName);
  }
}