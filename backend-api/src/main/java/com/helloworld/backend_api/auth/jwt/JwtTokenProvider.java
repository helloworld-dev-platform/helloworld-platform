package com.helloworld.backend_api.auth.jwt;

import com.helloworld.backend_api.user.domain.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT 생성 및 검증 유틸리티
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

  private final JwtProperties jwtProperties;//jwt 설정값 자동바인딩(DTO)
  private SecretKey secretKey; //시크릿키객체

  //의존성주입 후, 시크릿키 초기화 메서드 실행함
  @PostConstruct
  public void init() {
    String secret = jwtProperties.getSecretKey();
    log.info("[JWT-DEBUG] Loaded Secret Key: [{}]", secret);
    
    if (jwtProperties.getSecretKey() == null) {
      throw new IllegalArgumentException("JWT시크릿 키가 null입니다.");
    } else {
      byte[] keyBytes = Decoders.BASE64.decode(this.jwtProperties.getSecretKey());
      this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }
  }

  /**
   * 액세스 토큰 생성
   */
  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtProperties.getExpirationTime());

    return Jwts.builder()
        .setSubject(Long.toString(user.getId()))
        .claim("role", user.getUserRole())
        .claim("provider", user.getProvider())
        .claim("email", user.getUserEmail())
        .claim("username", user.getUserName())
        .setIssuedAt(new Date())
        .setExpiration(expiryDate)
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * 리프레시 토큰 생성
   */
  public String generateRefreshToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + 1000L * 60 * 60 * 24 * 7); // 7일

    return Jwts.builder()
        .setSubject(Long.toString(user.getId()))
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(secretKey, SignatureAlgorithm.HS256)
        .compact();
  }

  /**
   * 토큰 무결성 검사 (토큰 위조 검증)
   *
   * @param token
   * @description true : 위조 , false : 위조되지 않음
   */
  public boolean isTampered(String token) {
    try {
      Jws<Claims> claims = Jwts.parserBuilder()
          .setSigningKey(secretKey)
          .build()
          .parseClaimsJws(token);
      return false;
    } catch (JwtException | IllegalArgumentException e) {
      return true;
    }
  }

  /**
   * 토큰 만료여부 확인
   *
   * @param token
   * @description true: 만료 , false : 유효
   */
  public boolean isExpired(String token) {
    try {
      Claims claims = getClaims(token);
      return claims.getExpiration().before(new Date());
    } catch (Exception e) {
      return true;
    }
  }

  /**
   * 토큰 유효성 검증(위조 검증 & 만료 체크)
   *
   * @param token
   */
  public boolean isValid(String token) {
    return !isTampered(token) && !isExpired(token);
  }

  /**
   * 토큰에서 claims 추출
   *
   * @param token
   */
  public Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * 토큰 남은 시간 계산
   */
  public long getTokenRemainingTime(String token) {
    Date expiration = getClaims(token).getExpiration();
    return expiration.getTime() - System.currentTimeMillis();
  }

  public Long getUserId(String token) {
    return Long.parseLong(getClaims(token).getSubject());
  }

}
