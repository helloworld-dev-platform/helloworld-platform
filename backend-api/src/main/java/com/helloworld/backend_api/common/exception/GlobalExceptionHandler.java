package com.helloworld.backend_api.common.exception;

import com.helloworld.backend_api.common.response.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

  //커스텀 예외 처리(도메인 기준)
  public ResponseEntity<Object> handleCustomException(ErrorCode e) {
    return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponseDto.from(e));
  }


  private ResponseEntity<Object> handleCustomException(ErrorCode errorCode, Exception e) {
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(ErrorResponseDto.of(errorCode, e));
  }

  private ResponseEntity<Object> handleCustomException(ErrorCode errorCode, String message) {
    return ResponseEntity.status(errorCode.getHttpStatus())
        .body(ErrorResponseDto.of(errorCode, message));
  }
}
