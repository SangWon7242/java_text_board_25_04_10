package com.sbs.java.board.global.interceptor;

import com.sbs.java.board.global.base.Rq;

public class NeedLoginInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if(rq.isLogined()) return true;

    return switch (rq.getActionPath()) {
      case "/usr/article/write",
          "/usr/article/modify",
          "/usr/article/delete",
          "/usr/member/logout",
          "/usr/member/mypage" -> {
        System.out.println("로그인 후 이용해주세요.");
        yield false;
      }

      default -> true;
    };
  }
}
