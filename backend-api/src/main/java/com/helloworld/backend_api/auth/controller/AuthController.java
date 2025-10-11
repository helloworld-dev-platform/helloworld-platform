package com.helloworld.backend_api.auth.controller;

import com.helloworld.backend_api.auth.dto.TokenReissueRequestDto;
import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.service.AuthService;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth", description = "인증/인가 및 토큰 관리 API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final RedisTokenService redisTokenService;
  private final AuthService authService;

  @Operation(
      summary = "토큰 재발급",
      description = "만료된 액세스 토큰을 리프레시 토큰으로 재발급한다."
  )
  @PostMapping("/reissue")
  @ApiErrorCodeExamples({
      ErrorCode.UNAUTHORIZED,
      ErrorCode.TOKEN_EXPIRED,
      ErrorCode.TOKEN_INVALID,
      ErrorCode.FORBIDDEN_ACCESS,
      ErrorCode.INTERNAL_SERVER_ERROR
  })
  public DataResponseDto<JwtTokenResponseDto> reissue(
      @RequestBody TokenReissueRequestDto requestDto) {

    JwtTokenResponseDto tokens = authService.reissueTokens(requestDto.getRefreshToken());
    return DataResponseDto.from(tokens);
  }


  @Operation(summary = "로그아웃", description = "현재 액세스 토큰을 블랙리스트 처리하고, Redis에 저장된 리프레시 토큰을 삭제한다.")
  @PostMapping("/logout")
  public DataResponseDto<Void> logout(@AuthenticationPrincipal User user,
      @RequestHeader("Authorization") String token) {
    String accessToken = token.replace("Bearer ", "");
    long expiration = jwtTokenProvider.getTokenRemainingTime(accessToken);
    redisTokenService.blacklistAccessToken(token, expiration);
    redisTokenService.deleteRefreshToken(user.getId());

    return DataResponseDto.from(null);
  }
}
