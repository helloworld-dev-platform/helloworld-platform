package com.helloworld.backend_api.common.swagger;

import com.helloworld.backend_api.common.exception.ErrorCode;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiErrorCodeExample {

  //todo: swagger Api 에러코드 응답예시 커스텀어노테이션 구현필요. SwaggerConfig에 커스터마이징해야됨.

  ErrorCode value();
}
