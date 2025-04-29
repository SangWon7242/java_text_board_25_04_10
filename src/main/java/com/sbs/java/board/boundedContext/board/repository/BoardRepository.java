package com.sbs.java.board.boundedContext.board.repository;

import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.boundedContext.board.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;
  private int lastId;

  public BoardRepository() {
    boards = new ArrayList<>();
    lastId = 0;
  }
  public Board findByBoardName(String name) {
    return boards.stream()
        .filter(board -> board.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  public Board findByBoardCode(String code) {
    return boards.stream()
        .filter(board -> board.getCode().equals(code))
        .findFirst()
        .orElse(null);
  }

  public void makeBoard(String name, String code) {
    int id = ++lastId;

    // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
    Board board = new Board(id, name, code);
    boards.add(board);
  }
}
