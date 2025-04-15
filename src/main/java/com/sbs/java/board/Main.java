package com.sbs.java.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

  static void makeArticleTestData(List<Article> articles) {
    /*
    articles.add(new Article(1, "제목1", "내용1"));
    articles.add(new Article(2, "제목2", "내용2"));
    articles.add(new Article(3, "제목3", "내용3"));
     */

    /*
    for(int i = 1; i <= 3; i++ ){
      articles.add(new Article(i, "제목" + i, "내용" + i);
    }
    */

    IntStream.rangeClosed(1, 3)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    List<Article> articles = new ArrayList<>();

    makeArticleTestData(articles);

    int lastArticleId = articles.get(articles.size() - 1).id;

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("텍스트 게시판을 시작합니다.");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if (cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();

        if (subject.trim().isEmpty()) {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();

        if (content.trim().isEmpty()) {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        int id = ++lastArticleId;

        // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
        Article article = new Article(id, subject, content);
        articles.add(article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
      } else if (cmd.equals("/usr/article/list")) {
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 리스트 ==");
        System.out.println("번호 | 제목");

        for(int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);

          System.out.printf("%d | %s\n", article.id, article.subject);
        }

      } else if (cmd.equals("/usr/article/detail")) {
        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        Article article = articles.get(articles.size() - 1);

        if (article == null) {
          System.out.println("원하는 게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("== 게시물 상세보기 ==");
        System.out.printf("번호 : %d\n", article.id);
        System.out.printf("제목 : %s\n", article.subject);
        System.out.printf("내용 : %s\n", article.content);
      } else if (cmd.equals("exit")) {
        System.out.println("텍스트 게시판을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 입력 된 명령어입니다.");
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");

    sc.close();
  }
}

class Article {
  int id;
  String subject;
  String content;

  Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}