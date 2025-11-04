package com.helloworld.backend_api.auth.service;

import static com.helloworld.backend_api.user.domain.UserStatus.ACTIVE;

import com.helloworld.backend_api.auth.dto.LoginRequest;
import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.model.PrincipalDetails;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
  private final AuthenticationManager authenticationManager;

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
    User user = findOrCreateUserByEmail(email, username, "USER");

    // 3. UserOauthCredential 엔티티 저장 (연동 정보 추가)
    UserOauthCredential credential = UserOauthCredential.builder()
        .user(user)
        .provider(provider)
        .providerId(providerId)
        .build();
    userOauthCredentialRepository.save(credential);

    return user;
  }

  /**
   * 기존 사용자인지 확인 후, 기존 사용자가 아니면 신규 User를 생성함
   *
   * @param username
   * @param email
   * @param role
   * @return
   */
  private User findOrCreateUserByEmail(String email, String username, String role) {

    return userRepository.findByUserEmail(email)
        .orElseGet(() -> {
          User newUser = User.builder()
              .userEmail(email)
              .userName(username) // Google에서 받은 이름 또는 안전한 기본값
              .userRole(role)     // "USER" (전달된 값)
              .status(ACTIVE) // UserStatus.ACTIVE ENUM 값 사용
              .totalPoint(0)
              .totalStudySecond(Long.valueOf(0))
              .build();
          return userRepository.save(newUser);
        });
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

  public JwtTokenResponseDto authenticateAndIssueToken(LoginRequest request) {
    // 1. 인증 로직 (책임: 인증 확인)
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    // 2. 사용자 객체 획득 (책임: 사용자 정보 획득)
    PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
    User user = principalDetails.getUser();

    // 3. 토큰 발급 및 저장 (책임: 토큰 처리)
    return generateAndSaveTokens(user, findLatestTestResult(user.getId()));
  }
}
