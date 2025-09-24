package com.flair.flair.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteTo {

    private String content;
    private LocalDateTime createdDate;
    private String author;
}
