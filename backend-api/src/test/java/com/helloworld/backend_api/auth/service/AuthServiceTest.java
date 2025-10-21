package com.helloworld.backend_api.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserPretestResult;
import com.helloworld.backend_api.user.repository.UserPreTestResultRepository;
import com.helloworld.backend_api.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

  // Mock 객체 선언
  @Mock
  private JwtTokenProvider jwtTokenProvider;
  @Mock
  private RedisTokenService redisTokenService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserPreTestResultRepository userPreTestResultRepository;

  // 테스트에서 사용할 상수 및 공통 객체
  private static final Long USER_ID = 1L;
  private static final String VALID_REFRESH_TOKEN = "valid-refresh-token";
  private static final String NEW_ACCESS_TOKEN = "new-access-token";
  private static final String NEW_REFRESH_TOKEN = "new-refresh-token";
  private User mockUser;
  private UserPretestResult mockTestResult;

  // 테스트 대상 객체
  @InjectMocks
  private AuthService authService;

  @BeforeEach
  void setUp() {
    //테스트 실행전 객체 초기화
    mockUser = User.builder().id(USER_ID).build();
    mockTestResult = UserPretestResult.builder().id(10L).build();
  }


  @Test
  @DisplayName("유효한 리프레시 토큰으로 토큰 재발급에 성공한다")
  void reissueTokens_Success() {
    // given - 유효한 리프레시 토큰이 주어졌을 때
    givenValidRefreshToken(VALID_REFRESH_TOKEN);

    // when - 토큰 재발급을 실행하면
    JwtTokenResponseDto result = authService.reissueTokens(VALID_REFRESH_TOKEN);

    // then - 새로운 토큰들이 정상적으로 발급되어야 한다
    assertThat(result.getAccessToken()).isEqualTo(NEW_ACCESS_TOKEN);
    assertThat(result.getRefreshToken()).isEqualTo(NEW_REFRESH_TOKEN);

    // then - 토큰 생성 시 '사전 테스트 결과'가 잘 전달되었는지 검증한다
    verify(jwtTokenProvider).generateToken(mockUser, Optional.of(mockTestResult));
    // then - 새로운 리프레시 토큰이 Redis에 저장되었는지 검증한다
    verify(redisTokenService).saveRefreshToken(eq(USER_ID), eq(NEW_REFRESH_TOKEN), anyLong());
  }

  @Test
  @DisplayName("Redis에 저장된 토큰과 일치하지 않으면 예외가 발생한다")
  void reissueTokens_Fail_InvalidTokenInRedis() {
    // given - Redis 검증에 실패하는 리프레시 토큰이 주어졌을 때
    String invalidToken = "invalid-in-redis-token";
    when(jwtTokenProvider.isTampered(invalidToken)).thenReturn(false);
    when(jwtTokenProvider.getUserId(invalidToken)).thenReturn(USER_ID);
    when(redisTokenService.isValid(USER_ID, invalidToken)).thenReturn(
        false); // Redis 검증 실패 상황 Mocking

    // when & then - 토큰 재발급을 실행하면 UNAUTHORIZED 예외가 발생해야 한다
    assertThatThrownBy(() -> authService.reissueTokens(invalidToken))
        .isInstanceOf(CustomException.class)
        .hasFieldOrPropertyWithValue("errorCode", ErrorCode.UNAUTHORIZED);
  }

  /**
   * 책임 분리: '유효한 리프레시 토큰'이 주어졌을 때의 모든 Mock 객체 행동을 정의하는 헬퍼 메소드
   */
  private void givenValidRefreshToken(String refreshToken) {
    when(jwtTokenProvider.isTampered(refreshToken)).thenReturn(false);
    when(jwtTokenProvider.getUserId(refreshToken)).thenReturn(USER_ID);
    when(redisTokenService.isValid(USER_ID, refreshToken)).thenReturn(true);
    when(userRepository.findById(USER_ID)).thenReturn(Optional.of(mockUser));
    when(userPreTestResultRepository.findFirstByUserIdOrderByCompletedAtDesc(USER_ID))
        .thenReturn(Optional.of(mockTestResult));
    when(jwtTokenProvider.generateToken(mockUser, Optional.of(mockTestResult))).thenReturn(
        NEW_ACCESS_TOKEN);
    when(jwtTokenProvider.generateRefreshToken(mockUser)).thenReturn(NEW_REFRESH_TOKEN);
  }
}