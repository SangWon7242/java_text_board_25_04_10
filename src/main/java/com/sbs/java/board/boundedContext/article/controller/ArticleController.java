package com.sbs.java.board.boundedContext.article.controller;

import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.container.Container;
import com.sbs.java.board.global.base.Rq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleController {
  public List<Article> articles;
  public int lastId;

  public ArticleController() {
    articles = new ArrayList<>();

    makeArticleTestData();

    lastId = articles.get(articles.size() - 1).id;
  }

  void makeArticleTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  public void doWrite() {
    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    if (subject.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if (content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    int id = ++lastId;

    // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
    Article article = new Article(id, subject, content);
    articles.add(article);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    Map<String, String> params = rq.getParams();

    // 검색 시작
    // articles : 정렬되지 않은 1 ~ 100 게시물 객체를 품고 있는 리스트
    List<Article> filteredArticles = new ArrayList<>(articles);

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticles = articles.stream()
          .filter(article -> article.subject.contains(searchKeyword) || article.content.contains(searchKeyword))
          .collect(Collectors.toList());
    }
    // 검색 끝


    // 정렬 로직
    List<Article> sortedArticles = filteredArticles;

    if (params.containsKey("orderBy")) {
      String orderBy = params.get("orderBy");

      switch (orderBy) {
        case "idAsc":
          sortedArticles.sort((a1, a2) -> a1.id - a2.id);
          break;
        case "idDesc":
        default:
          sortedArticles.sort((a1, a2) -> a2.id - a1.id);
          break;
      }
    } else {
      // /usr/article/list 라고만 입력이 된 경우를 대비
      sortedArticles.sort((a1, a2) -> a2.id - a1.id);
    }
    // 정렬 끝

    System.out.printf("== 게시물 리스트(총 %d개) ==\n", sortedArticles.size());
    System.out.println("번호 | 제목");

    sortedArticles.forEach(
        article -> System.out.printf("%d | %s\n", article.id, article.subject)
    );
  }

  public void showDetail(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if (id > articles.size()) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 상세보기 ==\n", id);
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.subject);
    System.out.printf("내용 : %s\n", article.content);
  }

  public void doModify(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    if (id > articles.size()) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.printf("== %d번 게시물 수정 ==\n", id);

    System.out.print("새 제목 : ");
    article.subject = Container.sc.nextLine();

    System.out.print("새 내용 : ");
    article.content = Container.sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(Rq rq) {
    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    if (articles.isEmpty()) {
      System.out.println("게시물이 존재하지 않습니다.");
      return;
    }

    Article article = findById(id, articles);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(article);
    System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);
  }

  public Article findById(int id, List<Article> articles) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst()
        .orElse(null);
  }
}
