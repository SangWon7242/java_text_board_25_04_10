package com.sbs.java.board.global.session;

import java.util.HashMap;
import java.util.Map;

public class Session {
  private Map<String, Object> sessionStore;

  public Session() {
    sessionStore = new HashMap<>();
  }

  // 세션 저장
  public void setAttribute(String attrName, Object value) {
    sessionStore.put(attrName, value);
  }
  
  // 세션 가져오기
  public Object getAttribute(String attrName) {
    return sessionStore.get(attrName);
  }

  // 세션 존재여부
  public boolean hasAttribute(String attrName) {
    return sessionStore.containsKey(attrName);
  }
  
  // 세션 제거
  public void removeAttribute(String attrName) {
    sessionStore.remove(attrName);
  }
}
