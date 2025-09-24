package com.flair.flair.model;

import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteTo {

  private String content;
  private LocalDateTime createdAt;
  private String author;
}
