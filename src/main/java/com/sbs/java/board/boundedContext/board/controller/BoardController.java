package com.sbs.java.board.boundedContext.board.controller;

import com.sbs.java.board.boundedContext.board.dto.Board;
import com.sbs.java.board.boundedContext.board.service.BoardService;
import com.sbs.java.board.boundedContext.controller.Controller;
import com.sbs.java.board.boundedContext.member.dto.Member;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;

import java.util.List;

public class BoardController implements Controller {
  private BoardService boardService;

  public BoardController() {
    boardService = Container.boardService;
  }

  @Override
  public void performAction(Rq rq) {
    if (rq.getActionPath().equals("/adm/board/makeBoard")) {
      doMakeBoard(rq);
    }
    if (rq.getActionPath().equals("/usr/board/list")) {
      showBoardList(rq);
    }
  }

  private void showBoardList(Rq rq) {
    System.out.println("== 게시판 목록 ==");

    System.out.println("번호 | 이름 | 코드 | 게시물 수");
    System.out.println("_".repeat(30));

    List<Board> boards = boardService.findAll();

    if (boards.isEmpty()) {
      System.out.println("게시판이 존재하지 않습니다.");
      return;
    }

    boards.forEach(
        board -> {
          int articleCount = Container.articleService.getArticleCountByBoardId(board.getId());
          System.out.printf("%d | %s | %s | %d\n", board.getId(), board.getName(), board.getCode(), articleCount);
        }
    );

  }

  private void doMakeBoard(Rq rq) {
    System.out.println("== 게시판 생성 ==");

    Member member = rq.getLoginedMember();

    if (!member.isAdmin()) {
      System.out.println("관리자만 게시판 생성이 가능합니다.");
      return;
    }

    System.out.print("게시판 이름 : ");
    String boardName = Container.sc.nextLine();

    if (boardName.trim().isEmpty()) {
      System.out.println("boardName(을)를 입력해주세요.");
      return;
    }

    Board boardByName = boardService.findByBoardName(boardName);

    if (boardByName != null) {
      System.out.printf("'%s' 게시판은 이미 존재하는 게시판입니다.\n", boardName);
      return;
    }

    System.out.println("=== 게시판 코드 예시 ===");
    System.out.println("예: free, notice, qna, etc...");
    System.out.println("=".repeat(20));

    System.out.print("게시판 코드(영문) : ");
    String boardCode = Container.sc.nextLine();

    if (boardCode.trim().isEmpty()) {
      System.out.println("boardCode(을)를 입력해주세요.");
      return;
    }

    Board boardByCode = boardService.findByBoardCode(boardCode);

    if (boardByCode != null) {
      System.out.printf("'%s' 코드는 사용중인 코드입니다.\n", boardCode);
      return;
    }

    boardService.makeBoard(boardName, boardCode);

    System.out.printf("'%s' 게시판이 생성되었습니다.\n", boardName);
  }
}
