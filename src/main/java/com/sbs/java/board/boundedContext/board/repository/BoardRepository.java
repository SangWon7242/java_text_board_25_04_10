package com.sbs.java.board.boundedContext.board.repository;

import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.boundedContext.board.dto.Board;
import com.sbs.java.board.global.util.Util;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;
  private int lastId;

  public BoardRepository() {
    boards = new ArrayList<>();

    makeTestDate();

    lastId = boards.get(boards.size() - 1).getId();
  }

  public void makeTestDate() {
    makeBoard("자유게시판", "free");
    makeBoard("공지사항", "notice");
    makeBoard("질의응답", "qna");
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

  public Board findByBoardId(int id) {
    return boards.stream()
        .filter(board -> board.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public void makeBoard(String name, String code) {
    int id = ++lastId;

    String regDate = Util.getNowDateStr();
    String updateDate = Util.getNowDateStr();

    Board board = new Board(id, regDate, updateDate, name, code);
    boards.add(board);
  }

  public List<Board> findAll() {
    return boards;
  }
}
