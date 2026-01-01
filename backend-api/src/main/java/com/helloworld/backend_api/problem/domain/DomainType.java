package com.helloworld.backend_api.problem.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DomainType {
  STEPUP("STEPUP", "스텝업코스"),
  LEVEL_TEST("LEVEL_TEST", "사전레벨테스트");

  private final String code;
  private final String description;
}
