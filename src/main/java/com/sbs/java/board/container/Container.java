package com.sbs.java.board.container;

import com.sbs.java.board.boundedContext.article.controller.ArticleController;
import com.sbs.java.board.boundedContext.article.repository.ArticleRepository;
import com.sbs.java.board.boundedContext.article.service.ArticleService;
import com.sbs.java.board.boundedContext.member.controller.MemberController;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleRepository articleRepository;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  // 프로그램 실행시 딱 한번 실행
  static {
    sc = new Scanner(System.in);

    articleRepository = new ArticleRepository();

    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
