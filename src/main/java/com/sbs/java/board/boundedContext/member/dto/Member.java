package com.sbs.java.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
  private int id;
  private String username;
  private String password;
  private String name;
}
