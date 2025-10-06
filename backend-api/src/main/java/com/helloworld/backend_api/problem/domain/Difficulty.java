package com.helloworld.backend_api.problem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Difficulty {
  EASY("EASY", "쉬움"),
  MEDIUM("MEDIUM", "보통"),
  HARD("HARD", "어려움");

  private final String code;
  private final String description;
}
