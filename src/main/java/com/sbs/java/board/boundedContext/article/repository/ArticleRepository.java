package com.sbs.java.board.boundedContext.article.repository;

import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.global.util.Util;

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
        .forEach(
            i -> write("제목" + i, "내용" + i, 1, 1, "익명", "자유게시판"));
  }

  public int write(String subject, String content, int memberId, int boardId, String writerName, String boardName) {
    int id = ++lastId;

    String regDate = Util.getNowDateStr();
    String updateDate = Util.getNowDateStr();

    // 객체 생성 후, 객체가 가지고 있는 변수에 데이터 저장
    Article article = new Article(id, regDate, updateDate, subject, content, memberId, boardId, writerName, boardName, 0);
    articles.add(article);

    return id;
  }

  public List<Article> findAll() {
    return articles;
  }

  public List<Article> findAll(String searchKeywordTypeCode, String searchKeyword, String orderBy, int boardId) {
    List<Article> filteredArticles = findAll();

    // 게시판에 맞는 게시물 리스팅
    filteredArticles = filterByBoardId(filteredArticles, boardId);

    // 검색코드와 검색어에 맞는 게시물 리스팅
    filteredArticles = filterByKeyword(filteredArticles, searchKeyword, searchKeywordTypeCode);

    // 정렬된 게시물 리스팅
    filteredArticles = sortArticles(filteredArticles, orderBy);

    // 정렬 수행
    return filteredArticles;
  }

  private List<Article> filterByBoardId(List<Article> articles, int boardId) {
    if(boardId <= 0) {
      return articles;
    }

    return findAll().stream()
        .filter(article -> article.getBoardId() == boardId)
        .collect(Collectors.toList());
  }


  // 검색 로직을 담당
  private List<Article> filterByKeyword(List<Article> articles, String searchKeyword, String searchKeywordTypeCode) {
    if(searchKeyword.isEmpty()) {
      return articles;
    }

    /*
    // v1
    return articles.stream()
        .filter(article -> {
          switch (searchKeywordTypeCode) {
            case "subject":
              System.out.println("subject 실행");
              return article.getSubject().contains(searchKeyword);
            case "content":
              System.out.println("content 실행");
              return article.getContent().contains(searchKeyword);
            case "subject,content":
            default:
              System.out.println("subject,content 실행");
              return article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword);
          }
        })
        .collect(Collectors.toList());
     */

    // v2
    return articles.stream()
        .filter(article -> {
          return switch (searchKeywordTypeCode) {
            case "subject" -> article.getSubject().contains(searchKeyword);
            case "content" -> article.getContent().contains(searchKeyword);
            default -> article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword);
          };
        })
        .collect(Collectors.toList());
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

    article.setUpdateDate(Util.getNowDateStr());
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

  public int getArticleCountByBoardId(int boardId) {
    return (int) articles.stream()
        .filter(article -> article.getBoardId() == boardId)
        .count();
  }

  public void increaseHit(int id) {
    Article article = findById(id);

    article.setHit(article.getHit() + 1);
  }
}
