package com.helloworld.backend_api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FriendRequestStatus {
  PENDING("PENDING", "요청"),
  ACCEPTED("ACCEPTED", "수락"),
  REJECTED("REJECTED", "거절");

  private final String code;
  private final String description;
}
