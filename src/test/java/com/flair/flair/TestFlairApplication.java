package com.flair.flair;

import org.springframework.boot.SpringApplication;

public class TestFlairApplication {

  public static void main(String[] args) {
    SpringApplication.from(FlairApplication::main)
        .with(TestcontainersConfiguration.class)
        .run(args);
  }
}
