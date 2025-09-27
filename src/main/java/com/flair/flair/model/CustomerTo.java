package com.flair.flair.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTo {

  private String name;
  private String mobileNumber;
  private String email;
}
