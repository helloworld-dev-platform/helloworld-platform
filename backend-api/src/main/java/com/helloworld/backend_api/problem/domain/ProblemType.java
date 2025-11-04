package com.helloworld.backend_api.problem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProblemType {
  MULTIPLE_CHOICE("CHOICE", "객관식"),
  SUBJECTIVE("SUBJECTIVE", "주관식"),
  FILL_IN_THE_BLANK("FILL_IN_THE_BLANK", "빈칸채우기");

  private final String code;
  private final String description;
}
