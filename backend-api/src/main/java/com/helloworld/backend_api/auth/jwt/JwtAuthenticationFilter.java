package com.helloworld.backend_api.auth.jwt;

import com.helloworld.backend_api.auth.service.RedisTokenService;
import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT 인증 필터 : 모든 요청마다 JWT 확인 후, SecurityContext 에 인증정보 등록
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  private final RedisTokenService redisTokenSevice;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    String token = resolveToken(request);

    if (token != null && jwtTokenProvider.isValid(token)) {

      if (redisTokenSevice.isBlacklisted(token)) {
        throw new CustomException(ErrorCode.UNAUTHORIZED);
      }
      Claims claims = jwtTokenProvider.getClaims(token);
      Long userId = jwtTokenProvider.getUserId(token);
      User user = userRepository.findById(userId).orElse(null);

      if (user != null) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            user, null, Collections.singleton(() -> user.getUserRole()));
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }
    filterChain.doFilter(request, response);
  }

  private String resolveToken(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    }
    return null;
  }
}
