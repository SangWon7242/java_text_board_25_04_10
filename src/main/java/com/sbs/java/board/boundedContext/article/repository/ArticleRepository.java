package com.sbs.java.board.boundedContext.article.repository;

import com.sbs.java.board.boundedContext.article.dto.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
  public List<Article> articles;
  public int lastId;

  public ArticleRepository() {
    articles = new ArrayList<>();

    makeTestData();

    lastId = articles.get(articles.size() - 1).getId();
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> write("제목" + i, "내용" + i, 1, "익명"));
  }

  public int write(String subject, String content, int memberId, String writerName) {
    int id = ++lastId;

    // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
    Article article = new Article(id, subject, content, memberId, writerName);
    articles.add(article);

    return id;
  }

  public List<Article> findAll() {
    return articles;
  }

  public List<Article> findAll(String searchKeyword, String orderBy) {
    // 검색 수행
    List<Article> filteredArticles = filterByKeyword(searchKeyword);

    // 정렬 수행
    return sortArticles(filteredArticles, orderBy);
  }
  
  
  // 검색 로직을 담당
  private List<Article> filterByKeyword(String searchKeyword) {
    List<Article> filteredArticles = findAll();

    if (!searchKeyword.isEmpty()) {
      filteredArticles = articles.stream()
          .filter(article -> article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword))
          .collect(Collectors.toList());
    }

    return filteredArticles;
  }

  // 정렬 로직을 담당
  private List<Article> sortArticles(List<Article> articles, String orderBy) {
    List<Article> sortedArticles = new ArrayList<>(articles);

    if (!orderBy.isEmpty()) {
      switch (orderBy) {
        case "idAsc":
          sortedArticles.sort((a1, a2) -> a1.getId() - a2.getId());
          break;
        case "idDesc":
        default:
          sortedArticles.sort((a1, a2) -> a2.getId() - a1.getId());
          break;
      }
    }

    return sortedArticles;
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    article.setSubject(subject);
    article.setContent(content);
  }

  public void delete(int id) {
    Article article = findById(id);

    if(article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    };

    articles.remove(article);
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
