package com.sbs.java.board.container;

import com.sbs.java.board.boundedContext.article.controller.ArticleController;
import com.sbs.java.board.boundedContext.article.service.ArticleService;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleService articleService;
  public static ArticleController articleController;

  // 프로그램 실행시 딱 한번 실행
  static {
    sc = new Scanner(System.in);

    articleService = new ArticleService();

    articleController = new ArticleController();
  }
}
