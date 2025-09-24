package com.flair.flair.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoteTo {

  private Long noteId;
  private String content;
}
