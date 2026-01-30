package com.helloworld.backend_api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.backend_api.auth.jwt.AccessTokenResponseDto;
import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.leveltest.domain.UserLevelTestResult;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserLevelTestResultRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;
  private final ObjectMapper objectMapper;
  private final UserLevelTestResultRepository userLevelTestResultRepository;

  private static final int ACCESS_TOKEN_EXPIRATION_SECONDS = 1800; //30분
  private static final int REFRESH_TOKEN_EXPIRATION_DAYS = 7; //7일


  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    // 1단계: 사용자 정보 추출
    User user = extractUserInfo(authentication);

    // 2단계: 부가 정보(사전 테스트 결과) 조회
    Optional<UserLevelTestResult> latestTestResult = findLatestTestResult(user);

    // 3단계: 토큰 발급 및 저장
    JwtTokenResponseDto tokens = issueAndSaveToken(user, latestTestResult);

    // 4단계: 쿠키에 정보 담기
    addCookie(response, "refreshToken", tokens.getRefreshToken(),
        REFRESH_TOKEN_EXPIRATION_DAYS * 24 * 60 * 60);

    AccessTokenResponseDto atResponse = new AccessTokenResponseDto(tokens.getAccessToken());
    sendSuccessResponse(response, atResponse);

  }

  /**
   * Authentication 객체에서 사용자정보를 추출한다.
   *
   * @param authentication
   * @return
   */
  private User extractUserInfo(Authentication authentication) {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    return principal.getUser();
  }

  /**
   * User 객체에서 가장 최근 사전레벨테스트 결과를 가져온다.
   *
   * @param user
   * @return
   */
  private Optional<UserLevelTestResult> findLatestTestResult(User user) {
    return userLevelTestResultRepository.findFirstByUserIdOrderByCompletedAtDesc(user.getId());
  }

  private JwtTokenResponseDto issueAndSaveToken(User user,
      Optional<UserLevelTestResult> testResult) {
    String accessToken = jwtTokenProvider.generateToken(user, testResult);
    String refreshToken = jwtTokenProvider.generateRefreshToken(user);

    redisTokenService.saveRefreshToken(user.getId(), refreshToken, 1000L * 60 * 60 * 24 * 7);

    return new JwtTokenResponseDto(accessToken, refreshToken);
  }

  private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(true);   // JavaScript에서 접근 불가
    //cookie.setSecure(false);     // HTTPS 통신에서만 전송 (운영 환경에서는 true로 설정)
    cookie.setPath("/");        // 웹사이트 전체 경로에서 유효
    cookie.setMaxAge(maxAge);   // 쿠키 만료 시간 설정
    response.addCookie(cookie);
  }


  private void sendSuccessResponse(HttpServletResponse response, AccessTokenResponseDto accessToken)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(accessToken));
  }
}
