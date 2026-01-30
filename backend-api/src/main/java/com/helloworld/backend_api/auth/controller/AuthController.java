package com.helloworld.backend_api.auth.controller;

import com.helloworld.backend_api.auth.dto.LoginRequest;
import com.helloworld.backend_api.auth.dto.TokenReissueRequestDto;
import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.service.AuthService;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.oauth.dto.GoogleLoginRequest;
import com.helloworld.backend_api.oauth.service.GoogleLoginService;
import com.helloworld.backend_api.user.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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

  private final GoogleLoginService googleLoginService;
  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;
  private final AuthService authService;

  private static final int REFRESH_TOKEN_EXPIRATION_DAYS = 7;

  @Operation(summary = "일반 로그인 (이메일/비밀번호)", description = "이메일과 비밀번호로 로그인하고 AT/RT를 발급받습니다.")
  @ApiErrorCodeExamples({
      ErrorCode.UNAUTHORIZED,
      ErrorCode.TOKEN_EXPIRED,
      ErrorCode.TOKEN_INVALID,
      ErrorCode.FORBIDDEN_ACCESS,
      ErrorCode.INTERNAL_SERVER_ERROR
  })
  @PostMapping("/login")
  public DataResponseDto<JwtTokenResponseDto> login(
      @RequestBody @Valid LoginRequest request,
      HttpServletResponse response) {

    JwtTokenResponseDto tokens = authService.authenticateAndIssueToken(request);

    addCookie(response, "refreshToken", tokens.getRefreshToken(),
        REFRESH_TOKEN_EXPIRATION_DAYS * 24 * 60 * 60);

//    AccessTokenResponseDto atResponse = new AccessTokenResponseDto(tokens.getAccessToken());

    return DataResponseDto.from(tokens);
  }

  @PostMapping("/google/login")
  public DataResponseDto<JwtTokenResponseDto> googleLogin(
      @RequestBody GoogleLoginRequest request,
      HttpServletResponse response) {

    // 1. 구글과 통신하여 사용자 정보를 가져오고, 우리 서비스의 토큰을 발급받습니다.
    String code = request.getAuthorizationCode();
    String decodedCode = URLDecoder.decode(code, StandardCharsets.UTF_8);

    JwtTokenResponseDto tokens = googleLoginService.login(decodedCode);

    // 2. Refresh Token은 HttpOnly 쿠키로 응답 헤더에 추가합니다.
    addCookie(response, "refreshToken", tokens.getRefreshToken(),
        REFRESH_TOKEN_EXPIRATION_DAYS * 24 * 60 * 60);

    // 3. Access Token은 JSON 본문으로 응답합니다.
//    AccessTokenResponseDto atResponse = new AccessTokenResponseDto(tokens.getAccessToken());
    return DataResponseDto.from(tokens);
  }

  private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(true);
    //cookie.setSecure(true); // HTTPS 환경에서만 전송
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
  }

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
