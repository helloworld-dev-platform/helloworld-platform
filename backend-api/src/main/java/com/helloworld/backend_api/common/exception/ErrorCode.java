package com.helloworld.backend_api.common.exception;

import java.util.Optional;
import java.util.function.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  // Success
  OK("SUCCESS", HttpStatus.OK, "OK"),

  //USER 도메인
  USER_NOT_FOUND("USER_001", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
  DUPLICATE_EMAIL("USER_002", HttpStatus.CONFLICT, "이미 존재하는 이메일 입니다."),

  //AUTH 도메인
  UNAUTHORIZED("AUTH_001", HttpStatus.UNAUTHORIZED, "인증 정보가 없습니다."),
  TOKEN_EXPIRED("AUTH_002", HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),
  FORBIDDEN_ACCESS("AUTH_003", HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
  OAUTH_PROVIDER_ERROR("AUTH_004", HttpStatus.BAD_REQUEST, "OAuth 제공자 오류입니다."),
  TOKEN_INVALID("AUTH_005", HttpStatus.UNAUTHORIZED, "유효한 토큰이 아닙니다."),

  INTERNAL_SERVER_ERROR("COMMON_001", HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류입니다.");

  private final String code;
  private final HttpStatus httpStatus;
  private final String message;

  public String getMessage(Throwable throwable) {
    return this.getMessage(this.getMessage(this.getMessage() + " - " + throwable.getMessage()));
  }

  public String getMessage(String message) {
    return Optional.ofNullable(message)
        .filter(Predicate.not(String::isBlank))
        .orElse(this.getMessage());
  }

}
