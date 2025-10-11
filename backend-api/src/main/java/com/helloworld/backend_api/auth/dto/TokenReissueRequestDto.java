package com.helloworld.backend_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "토큰 재발급 요청 DTO")
public class TokenReissueRequestDto {

  @Schema(description = "만료되지 않은 리프레시 토큰", example = "eyJhbGciOiJI...")
  private String refreshToken;
}
