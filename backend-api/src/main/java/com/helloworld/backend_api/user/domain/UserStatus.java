package com.helloworld.backend_api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {
  ACTIVE("ACTIVE", "정상"),
  DORMANT("DORMANT", "휴면"),
  WITHDRAWN("WITHDRAWN", "탈퇴");

  private final String code;
  private final String description;
}
