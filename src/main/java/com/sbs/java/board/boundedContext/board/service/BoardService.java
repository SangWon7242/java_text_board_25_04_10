package com.sbs.java.board.boundedContext.board.service;

import com.sbs.java.board.boundedContext.board.dto.Board;
import com.sbs.java.board.boundedContext.board.repository.BoardRepository;
import com.sbs.java.board.container.Container;

import java.util.List;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.boardRepository;
  }

  public Board findByBoardName(String name) {
    return boardRepository.findByBoardName(name);
  }

  public Board findByBoardCode(String code) {
    return boardRepository.findByBoardCode(code);
  }

  public void makeBoard(String name, String code) {
    boardRepository.makeBoard(name, code);
  }

  public List<Board> findAll() {
    return boardRepository.findAll();
  }

  public Board findByBoardId(int id) {
    return boardRepository.findByBoardId(id);
  }
}
