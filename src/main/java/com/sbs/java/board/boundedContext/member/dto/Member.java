package com.sbs.java.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class Member {
  private final int id;
  private final String username;
  private String password;
  private String name;

  public String getType() {
    return isAdmin() ? "관리자" : "일반회원";
  }

  private boolean isAdmin() {
    return username.equals("admin");
  }
}
