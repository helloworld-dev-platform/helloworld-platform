package com.helloworld.backend_api.auth.controller;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.user.domain.Users;
import com.helloworld.backend_api.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
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
  public DataResponseDto<Map<String, String>> reissue(
      @RequestBody Map<String, String> body) {
    String refreshToken = body.get("refreshToken");
    //토큰 유효성 검사
    if (!jwtTokenProvider.isTampered(refreshToken)) {
      throw new CustomException(ErrorCode.TOKEN_INVALID);
    }

    Long userId = jwtTokenProvider.getUserId(refreshToken); //토큰에서 userId 추출
    //redis에 저장된 리프레시 토큰과 비교
    if (!redisTokenService.isValid(userId, refreshToken)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED);
    }
    //유저 정보 조회
    Users user = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    //새로운 토큰 발급
    String newAccessToken = jwtTokenProvider.generateToken(user);
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);
    //새로운 리프레시 토큰 redis에 저장(만료시간(TTL):7일)
    redisTokenService.saveRefreshToken(userId, newRefreshToken, 1000L * 60 * 60 * 24 * 7);
    //토큰을 담은 Map 생성 후 반환
    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put("accessToken", newAccessToken);
    tokenMap.put("refreshToken", newRefreshToken);

    return DataResponseDto.from(tokenMap);

  }


  @Operation(summary = "로그아웃", description = "현재 액세스 토큰을 블랙리스트 처리하고, Redis에 저장된 리프레시 토큰을 삭제한다.")
  @PostMapping("/logout")
  public DataResponseDto<Void> logout(@AuthenticationPrincipal Users user,
      @RequestHeader("Authorization") String token) {
    String accessToken = token.replace("Bearer ", "");
    long expiration = jwtTokenProvider.getTokenRemainingTime(accessToken);
    redisTokenService.blacklistAccessToken(token, expiration);
    redisTokenService.deleteRefreshToken(user.getId());

    return DataResponseDto.from(null);
  }
}
