package com.sbs.java.board.boundedContext.article.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private int id;
  private String subject;
  private String content;
  private String writerName;
}
