package com.sbs.java.board.container;

import com.sbs.java.board.boundedContext.article.controller.ArticleController;
import com.sbs.java.board.boundedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boundedContext.article.service.ArticleService;
import com.sbs.java.board.boundedContext.member.controller.MemberController;
import com.sbs.java.board.boundedContext.member.repository.MemberRepository;
import com.sbs.java.board.boundedContext.member.service.MemberService;
import com.sbs.java.board.global.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner sc;
  public static Session session;

  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  // 프로그램 실행시 딱 한번 실행
  static {
    sc = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
