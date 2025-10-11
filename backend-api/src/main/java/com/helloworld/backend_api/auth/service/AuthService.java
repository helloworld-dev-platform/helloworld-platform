package com.helloworld.backend_api.auth.service;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserPretestResult;
import com.helloworld.backend_api.user.repository.UserPreTestResultRepository;
import com.helloworld.backend_api.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;
  private final UserRepository userRepository;
  private final UserPreTestResultRepository userPreTestResultRepository;

  @Transactional
  public JwtTokenResponseDto reissueTokens(String refreshToken) {
    // 1단계: 리프레시 토큰 검증
    Long userId = validateAndGetUserId(refreshToken);

    // 2단계: 사용자 정보 조회
    User user = findUserById(userId);

    // 3단계: 사용자의 최근 사전 테스트 결과 조회
    Optional<UserPretestResult> latestTestResult = findLatestTestResult(userId);

    // 4단계: 새로운 토큰 발급 및 저장
    return generateAndSaveTokens(user, latestTestResult);
  }

  private Long validateAndGetUserId(String refreshToken) {
    if (jwtTokenProvider.isTampered(refreshToken)) {
      throw new CustomException(ErrorCode.TOKEN_INVALID);
    }

    Long userId = jwtTokenProvider.getUserId(refreshToken);
    if (!redisTokenService.isValid(userId, refreshToken)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED);
    }
    return userId;
  }

  private User findUserById(Long userId) {
    return userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
  }

  private Optional<UserPretestResult> findLatestTestResult(Long userId) {
    return userPreTestResultRepository.findFirstByUserIdOrderByCompletedAtDesc(userId);
  }

  private JwtTokenResponseDto generateAndSaveTokens(User user,
      Optional<UserPretestResult> testResult) {
    String newAccessToken = jwtTokenProvider.generateToken(user, testResult);
    String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

    redisTokenService.saveRefreshToken(user.getId(), newRefreshToken, 1000L * 60 * 60 * 24 * 7);

    return new JwtTokenResponseDto(newAccessToken, newRefreshToken);
  }
}
