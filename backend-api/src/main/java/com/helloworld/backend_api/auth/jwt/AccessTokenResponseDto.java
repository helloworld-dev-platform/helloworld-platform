package com.helloworld.backend_api.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccessTokenResponseDto {

  private final String accessToken;

}
