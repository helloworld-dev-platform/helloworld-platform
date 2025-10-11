package com.helloworld.backend_api.auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtTokenResponseDto {

  private final String accessToken;
  private final String refreshToken;
}
