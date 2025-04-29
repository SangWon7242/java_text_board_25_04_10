package com.sbs.java.board.boundedContext.member.repository;

import com.sbs.java.board.boundedContext.member.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MemberRepository {
  private List<Member> members;
  private int lastId;

  public MemberRepository() {
    members = new ArrayList<>();

    makeTestData();

    lastId = members.get(members.size() - 1).getId();
  }

  void makeTestData() {
    join("admin", "admin", "관리자");
    join("user1", "1234", "유저1");
    join("java_good", "5678", "자바최고");
  }

  public void join(String username, String password, String name) {
    int id = ++lastId;

    Member member = new Member(id, username, password, name);
    members.add(member);
  }

  public Member findByUsername(String username) {
    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }
}
