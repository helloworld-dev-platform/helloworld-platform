package com.helloworld.backend_api.auth;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.user.domain.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

  private final JwtTokenProvider jwtTokenProvider;
  private final RedisTokenService redisTokenService;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
    Users user = principal.getUser();

    String accessToken = jwtTokenProvider.generateToken(user);
    String refreshToken = jwtTokenProvider.generateRefreshToken(user);

    //redis에 refresh토큰 저장 (만료시간 7일)
    redisTokenService.saveRefreshToken(user.getId(), refreshToken, 1000L * 60 * 60 * 24 * 7);

    //todo: 추후, 리다이렉트 주소로 변동
    response.setContentType("application/json;charset=UTF-8");
    response.getWriter().write(
        "{\"accessToken\":\"" + accessToken + "\",\"refreshToken\":\"" + refreshToken + "\"}"
    );
  }
}
