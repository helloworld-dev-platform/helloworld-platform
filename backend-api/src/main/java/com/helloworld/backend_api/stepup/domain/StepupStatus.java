package com.helloworld.backend_api.stepup.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StepupStatus {
  NOT_STARTED("NOT_STARTED", "시작 전"),
  IN_PROGRESS("IN_PROGRESS", "진행 중"),
  COMPLETED("COMPLETED", "완료");

  private final String code;
  private final String description;
}
