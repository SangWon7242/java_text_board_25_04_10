package com.sbs.java.board.boundedContext.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Board {
  private final int id;
  private String name;
  private String code;
}
