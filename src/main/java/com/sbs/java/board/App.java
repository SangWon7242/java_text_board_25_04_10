package com.sbs.java.board;

import com.sbs.java.board.boundedContext.controller.Controller;
import com.sbs.java.board.boundedContext.member.dto.Member;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;
import com.sbs.java.board.global.interceptor.Interceptor;

import java.util.ArrayList;
import java.util.List;

public class App {
  void run() {
    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("텍스트 게시판을 시작합니다.");

    while (true) {
      Rq rq = new Rq();

      Member member = rq.getLoginedMember();

      String promptName = "명령어";

      if (member != null) {
        promptName = member.getUsername();
      }

      System.out.printf("%s) ", promptName);
      String cmd = Container.sc.nextLine();

      rq.setCommand(cmd);

      rq.getActionPath();

      if (!runInterceptor(rq)) {
        continue;
      }

      Controller controller = getControllerByRequestUri(rq);

      if (controller != null) {
        controller.performAction(rq);
      } else if (rq.getUrlPath().equals("exit")) {
        System.out.println("텍스트 게시판을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 된 명령어 입니다.");
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");

    Container.sc.close();
  }

  private Controller getControllerByRequestUri(Rq rq) {
    switch (rq.getControllerTypeCode()) {
      case "usr":
        switch (rq.getControllerName()) {
          case "article":
            return Container.articleController;
          case "member":
            return Container.memberController;
          case "board":
            return Container.boardController;
        }
      case "adm":
        switch (rq.getControllerName()) {
          case "board":
            return Container.boardController;
        }
    }

    return null;
  }

  private boolean runInterceptor(Rq rq) {
    List<Interceptor> interceptors = new ArrayList<>();

    interceptors.add(Container.needLoginInterceptor);
    interceptors.add(Container.needLogoutInterceptor);

    for (Interceptor interceptor : interceptors) {
      if (!interceptor.run(rq)) {
        return false;
      }
    }

    return true;
  }
}
