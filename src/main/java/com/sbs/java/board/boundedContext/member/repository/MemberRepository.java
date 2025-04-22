package com.sbs.java.board.boundedContext.member.repository;

import com.sbs.java.board.boundedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;
  private int lastId;

  public MemberRepository() {
    members = new ArrayList<>();
    lastId = 0;
  }

  public void join(String username, String password, String name) {
    int id = ++lastId;

    Member member = new Member(id, username, password, name);
    members.add(member);
  }
}
