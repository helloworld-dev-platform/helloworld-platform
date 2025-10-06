package com.helloworld.backend_api.common.exception;

import lombok.Getter;

@Getter
public class ErrorResponseDto {

  private final String code;
  private final String message;


  public ErrorResponseDto(ErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }

  // ErrorCode를 받아서 DTO를 생성하는 정적 메소드
  public static ErrorResponseDto of(ErrorCode errorCode) {
    return new ErrorResponseDto(errorCode);
  }
}
