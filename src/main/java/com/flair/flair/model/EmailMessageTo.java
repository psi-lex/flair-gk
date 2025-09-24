package com.flair.flair.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EmailMessageTo {

  private String to;
  private String subject;
  private String text;
}
