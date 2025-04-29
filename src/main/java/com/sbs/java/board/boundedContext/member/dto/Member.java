package com.sbs.java.board.boundedContext.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Member {
  private final int id;
  private String regDate;
  private String updateDate;
  private final String username;
  private String password;
  private String name;

  public String getType() {
    return isAdmin() ? "관리자" : "일반회원";
  }

  public boolean isAdmin() {
    return username.equals("admin");
  }
}
