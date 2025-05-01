package com.sbs.java.board.boundedContext.article.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
  private int id;
  private String regDate;
  private String updateDate;
  private String subject;
  private String content;
  private int memberId;
  private int boardId;
  private String writerName;
  private String boardName;
  private int hit;
}
