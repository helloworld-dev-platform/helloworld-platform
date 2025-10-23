package com.helloworld.backend_api.oauth.service;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.service.AuthService;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserPretestResult;
import com.helloworld.backend_api.user.repository.UserPreTestResultRepository;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleLoginService {

  @Value("${spring.security.oauth2.client.registration.google.client-id}")
  private String clientId;
  @Value("${spring.security.oauth2.client.registration.google.client-secret}")
  private String clientSecret;
  @Value("${oauth.redirect.frontend-url}")
  private String redirectUri;

  private final RestTemplate restTemplate = new RestTemplate();
  private final AuthService authService; // 3단계에서 리팩토링한 서비스
  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;
  private final UserPreTestResultRepository userPreTestResultRepository;

  public JwtTokenResponseDto login(String authorizationCode) {
    // 1. 인가 코드로 구글 토큰 받기
    String googleAccessToken = getGoogleAccessToken(authorizationCode);

    // 2. 구글 토큰으로 구글 사용자 정보 받기
    GoogleUserInfo googleUserInfo = getGoogleUserInfo(googleAccessToken);

    // 3. 우리 서비스에 로그인/회원가입 처리
    String provider = "google";
    String providerId = googleUserInfo.getSub();
    String username = provider + "_" + providerId;
    String email = googleUserInfo.getEmail();

    User user = authService.findOrCreateOauthUser(provider, providerId, email, username);

    // 4. 우리 서비스의 AT/RT 발급
    Optional<UserPretestResult> latestTestResult =
        userPreTestResultRepository.findFirstByUserIdOrderByCompletedAtDesc(user.getId());

    String accessToken = jwtTokenProvider.generateToken(user, latestTestResult);
    String refreshToken = jwtTokenProvider.generateRefreshToken(user);

    // 5. RT를 Redis에 저장
    redisTokenService.saveRefreshToken(user.getId(), refreshToken, 1000L * 60 * 60 * 24 * 7);

    return new JwtTokenResponseDto(accessToken, refreshToken);
  }

  // 구글 토큰 API 호출
  private String getGoogleAccessToken(String code) {
    String tokenUrl = "https://oauth2.googleapis.com/token";

    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("code", code);
    params.add("client_id", clientId);
    params.add("client_secret", clientSecret);
    params.add("redirect_uri", redirectUri);
    params.add("grant_type", "authorization_code");

    // GoogleTokenResponse.class는 구글 응답을 파싱할 DTO (아래 정의)
    GoogleTokenResponse response = restTemplate.postForObject(tokenUrl, params,
        GoogleTokenResponse.class);

    if (response == null || response.getAccess_token() == null) {
      throw new CustomException(ErrorCode.AUTH_ERROR);
    }
    return response.getAccess_token();
  }

  // 구글 사용자 정보 API 호출
  private GoogleUserInfo getGoogleUserInfo(String accessToken) {
    String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

    HttpHeaders headers = new HttpHeaders();
    headers.setBearerAuth(accessToken);
    HttpEntity<String> entity = new HttpEntity<>("", headers);

    GoogleUserInfo userInfo = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity,
        GoogleUserInfo.class).getBody();

    if (userInfo == null) {
      throw new CustomException(ErrorCode.AUTH_ERROR);
    }
    return userInfo;
  }

  // --- 구글 API 응답을 위한 내부 DTO ---
  @Getter
  @Setter
  private static class GoogleTokenResponse {

    private String access_token;
    private Integer expires_in;
    private String token_type;
    private String scope;
    private String id_token;
  }

  @Getter
  @Setter
  private static class GoogleUserInfo {

    private String sub; // 구글에서의 고유 사용자 ID
    private String name;
    private String email;
    private String picture;
  }
}
