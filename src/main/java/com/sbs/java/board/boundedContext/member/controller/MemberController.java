package com.sbs.java.board.boundedContext.member.controller;

import com.sbs.java.board.boundedContext.controller.Controller;
import com.sbs.java.board.boundedContext.member.dto.Member;
import com.sbs.java.board.boundedContext.member.service.MemberService;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;

public class MemberController implements Controller {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.memberService;
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionPath().equals("/usr/member/join")) {
      doJoin(rq);
    } else if (rq.getActionPath().equals("/usr/member/login")) {
      doLogin(rq);
    } else if (rq.getActionPath().equals("/usr/member/logout")) {
      doLogout(rq);
    } else if (rq.getActionPath().equals("/usr/member/mypage")) {
      showMyPage(rq);
    }
  }

  public void doJoin(Rq rq) {
    String username;
    String password;
    String passwordConfirm;
    String name;
    Member member;

    System.out.println("== 회원 가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if (member != null) {
        System.out.println("입력하신 로그인 아이디는 이미 존재합니다.");
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.print("비밀번호 : ");
      password = Container.sc.nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("비밀번호 확인 : ");
        passwordConfirm = Container.sc.nextLine();

        if (passwordConfirm.trim().isEmpty()) {
          System.out.println("비밀번호 확인을 입력해주세요.");
          continue;
        }

        if (!passwordConfirm.equals(password)) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }

        break;
      }

      break;
    }

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.sc.nextLine();

      if (name.trim().isEmpty()) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }

      break;
    }

    memberService.join(username, password, name);

    System.out.printf("'%s'님 회원 가입 되었습니다.\n", username);
  }

  public void doLogin(Rq rq) {
    String username;
    String password;
    Member member;

    System.out.println("== 로그인 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      username = Container.sc.nextLine();

      if (username.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      member = memberService.findByUsername(username);

      if (member == null) {
        System.out.printf("'%s'(은)는 존재하지 않는 로그인 아이디입니다.\n", username);
        continue;
      }

      break;
    }

    int tryPasswordMaxCount = 3;
    int tryPasswordCount = 0;

    // 로그인 비밀번호 입력
    while (true) {
      if (tryPasswordCount >= tryPasswordMaxCount) {
        System.out.println("비밀번호 다시 확인 후 입력해주세요.");
        return;
      }

      System.out.print("비밀번호 : ");
      password = Container.sc.nextLine();

      if (password.trim().isEmpty()) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      if (!member.getPassword().equals(password)) {
        System.out.println("비밀번호가 일치하지 않습니다.");
        tryPasswordCount++;

        System.out.printf("비밀번호 틀린 횟수(%d / %d)\n", tryPasswordCount, tryPasswordMaxCount);

        continue;
      }

      break;
    }

    rq.setSessionAttr("loginedMember", member);

    System.out.printf("'%s'님 로그인 되었습니다.\n", username);
  }

  private void doLogout(Rq rq) {
    if(rq.isLogout()) {
      System.out.println("로그인 후 이용해주세요.");
      return;
    }

    rq.removeSessionAttr("loginedMember");
    System.out.println("로그아웃 되었습니다.");
  }

  private void showMyPage(Rq rq) {
    Member member = (Member) rq.getSessionAttr("loginedMember");

    System.out.printf("== '%s'님의 정보 ==\n", member.getUsername());
    System.out.printf("로그인 아이디 : %s\n", member.getUsername());
    System.out.printf("이름 : %s\n", member.getName());
  }
}
