package com.helloworld.backend_api.common.exception;

import com.helloworld.backend_api.common.response.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  //커스텀 예외 처리(도메인 기준)

  @ExceptionHandler(CustomException.class)
  public ResponseEntity<ErrorResponseDto> handleCustomException(CustomException e) {
    log.error("handleCustomException:{}", e.getErrorCode());
    ErrorCode errorCode = e.getErrorCode();

    return new ResponseEntity<>(ErrorResponseDto.from(errorCode), errorCode.getHttpStatus());
  }

  /**
   * 위에서 처리하지 못한 모든 예외를 처리하는 핸들러 (최후의 보루)
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponseDto> handleException(Exception e) {
    log.error("handleException", e);
    ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
    ErrorResponseDto response = ErrorResponseDto.from(errorCode);
    return new ResponseEntity<>(response, errorCode.getHttpStatus());
  }
}
