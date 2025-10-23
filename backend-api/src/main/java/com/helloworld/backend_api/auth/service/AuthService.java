package com.helloworld.backend_api.auth.service;

import static com.helloworld.backend_api.user.domain.UserStatus.ACTIVE;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserOauthCredential;
import com.helloworld.backend_api.user.domain.UserPretestResult;
import com.helloworld.backend_api.user.repository.UserOauthCredentialRepository;
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
  private final UserOauthCredentialRepository userOauthCredentialRepository;
  private final UserPreTestResultRepository userPreTestResultRepository;

  @Transactional
  public User findOrCreateOauthUser(String provider, String providerId, String email,
      String username) {
    // 1. 기존 연동 정보 확인
    Optional<UserOauthCredential> credentialOptional =
        userOauthCredentialRepository.findByProviderAndProviderId(provider, providerId);

    if (credentialOptional.isPresent()) {
      return credentialOptional.get().getUser(); // 기존 사용자 반환
    }

    // 2. 연동 정보가 없으면, User를 찾거나 새로 생성
    User user = findOrCreateUser(username, email, "USER");

    // 3. UserOauthCredential 엔티티 저장 (연동 정보 추가)
    UserOauthCredential credential = UserOauthCredential.builder()
        .user(user)
        .provider(provider)
        .providerId(providerId)
        .build();
    userOauthCredentialRepository.save(credential);

    return user;
  }

  private User findOrCreateUser(String username, String email, String role) {
    User existingUser = userRepository.findByUserName(username);
    if (existingUser != null) {
      return existingUser;
    }

    // 신규 User 엔티티 생성 및 저장 (회원가입)
    User newUser = User.builder()
        .userEmail(email)
        .userName(username)
        .userRole(role)
        .totalPoint(0)
        .status(ACTIVE) // (UserStatus.ACTIVE)
        .build();
    return userRepository.save(newUser);
  }

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
