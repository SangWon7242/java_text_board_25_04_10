package com.sbs.java.board;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int lastArticleId = 0;

    System.out.println("== 자바 텍스트 게시판 ==");
    System.out.println("텍스트 게시판을 시작합니다.");

    while (true) {
      System.out.print("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.print("제목 : ");
        String subject = sc.nextLine();

        if(subject.trim().isEmpty()) {
          System.out.println("제목을 입력해주세요.");
          continue;
        }

        System.out.print("내용 : ");
        String content = sc.nextLine();

        if(content.trim().isEmpty()) {
          System.out.println("내용을 입력해주세요.");
          continue;
        }

        int id = ++lastArticleId;
        
        // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
        Article article = new Article();
        article.id = id;
        article.subject = subject;
        article.content = content;

        System.out.println("생성 된 게시물 객체 : " + article);

        System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
      }
      else if(cmd.equals("exit")) {
        System.out.println("텍스트 게시판을 종료합니다.");
        break;
      }
      else {
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
}