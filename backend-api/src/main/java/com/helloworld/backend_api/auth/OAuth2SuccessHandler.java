package com.helloworld.backend_api.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.jwt.JwtTokenResponseDto;
import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserPretestResult;
import com.helloworld.backend_api.user.repository.UserPreTestResultRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;
  private final ObjectMapper objectMapper;
  private final UserPreTestResultRepository userPreTestResultRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    // 1단계: 사용자 정보 추출
    User user = extractUserInfo(authentication);

    // 2단계: 부가 정보(사전 테스트 결과) 조회
    Optional<UserPretestResult> latestTestResult = findLatestTestResult(user);

    // 3단계: 토큰 발급 및 저장
    JwtTokenResponseDto tokens = issueAndSaveToken(user, latestTestResult);

    // 4단계: 클라이언트에 응답 전송
    sendSuccessResponse(response, tokens);

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
  private Optional<UserPretestResult> findLatestTestResult(User user) {
    return userPreTestResultRepository.findFirstByUserIdOrderByCompletedAtDesc(user.getId());
  }

  private JwtTokenResponseDto issueAndSaveToken(User user, Optional<UserPretestResult> testResult) {
    String accessToken = jwtTokenProvider.generateToken(user, testResult);
    String refreshToken = jwtTokenProvider.generateRefreshToken(user);

//redis에 refresh토큰 저장 (만료시간 7일)
    redisTokenService.saveRefreshToken(user.getId(), refreshToken, 1000L * 60 * 60 * 24 * 7);

    return new JwtTokenResponseDto(accessToken, refreshToken);
  }

  private void sendSuccessResponse(HttpServletResponse response, JwtTokenResponseDto tokens)
      throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(objectMapper.writeValueAsString(tokens));
  }
}
