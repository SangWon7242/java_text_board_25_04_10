package com.sbs.java.board.boundedContext.article.service;

import com.sbs.java.board.boundedContext.article.dto.Article;
import com.sbs.java.board.boundedContext.article.repository.ArticleRepository;
import com.sbs.java.board.container.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(String subject, String content) {
    return articleRepository.write(subject, content);
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public List<Article> findAll(String searchKeyword, String orderBy) {
    return articleRepository.findAll(searchKeyword, orderBy);
  }

  public void modify(int id, String subject, String content) {
    articleRepository.modify(id, subject, content);
  }


  public void delete(int id) {
    articleRepository.delete(id);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }
}
