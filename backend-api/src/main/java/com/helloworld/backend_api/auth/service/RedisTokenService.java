package com.helloworld.backend_api.auth.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

  //Redis 연결 스프링 빈
  private final String PREFIX = "RT:"; //리프레시토큰 식별용 접두사
  private final String BLACKLIST_PREFIX = "BL:"; //블랙리스트 식별용 접두사
  private final RedisTemplate<String, String> redisTemplate; //Redis 연결

  //리프레시 토큰 저장 메서드
  public void saveRefreshToken(Long user, String refreshToken, long expirationMillis) {
    ValueOperations<String, String> ops = redisTemplate.opsForValue();
    String key = PREFIX + user; //리프레시토큰 키값 => RT:user
    ops.set(key, refreshToken, Duration.ofMillis(expirationMillis)); //key-value, 유효기간 설정
  }

  //리프레시 토큰 조회
  public String getRefreshToken(Long user) {
    return redisTemplate.opsForValue().get(PREFIX + user); //RT:userID 키값으로 조회
  }

  //리프레시 토큰 삭제 (로그아웃시 사용)
  public void deleteRefreshToken(Long user) {
    redisTemplate.delete(PREFIX + user);
  }

  /**
   * 리프레시토큰과 요청된 토큰 비교 일치 : true 불일치 : false
   */
  public boolean isValid(Long user, String requestRefreshToken) {
    String saved = getRefreshToken(user); //저장된 토큰 가져옴
    return saved != null && saved.equals(requestRefreshToken); //요청토큰과 저장된 토큰 일치여부 확인
  }

  //블랙리스트 저장 (액세스토큰 무효화)
  public void blacklistAccessToken(String token, long expirationMillis) {
    redisTemplate.opsForValue().set(
        BLACKLIST_PREFIX + token, "logout", Duration.ofMillis(expirationMillis)
    );
  }

  public boolean isBlacklisted(String token) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(BLACKLIST_PREFIX + token));
  }
}
