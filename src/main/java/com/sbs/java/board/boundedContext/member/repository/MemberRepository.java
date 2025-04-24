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
    IntStream.rangeClosed(1, 3)
        .forEach(i -> join("user" + i, "user" + i, "유저" + i));
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
