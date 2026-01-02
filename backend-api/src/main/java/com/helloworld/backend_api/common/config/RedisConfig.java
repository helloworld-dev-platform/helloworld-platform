package com.helloworld.backend_api.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * com.helloworld.backend_api.common.config.RedisConfig
 *
 * @author      : User
 * @since       : 2026-01-02
 * @description :
 * ===============================================
 * DATE        AUTHOR          NOTE
 * ===============================================
 * 2026-01-02     User         최초 생성
 **/
@Configuration
public class RedisConfig {
	@Value("${spring.data.redis.host}")
	private String host;

	@Value("${spring.data.redis.port}")
	private int port;

	@Value("${spring.data.redis.username}") // 추가됨!
	private String username;

	@Value("${spring.data.redis.password}") // 추가됨!
	private String password;

	// 로컬 개발 환경 ("local" 프로파일일 때만 활성화)
	// SSH 터널링 때문에 이름 검사를 꺼야 함
	@Bean
	@Profile("local")
	public LettuceConnectionFactory localRedisConnectionFactory() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);

		redisConfig.setUsername(username);
		redisConfig.setPassword(RedisPassword.of(password));

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
			.useSsl()
			.disablePeerVerification() // 로컬에서만 보안 해제
			.build();

		return new LettuceConnectionFactory(redisConfig, clientConfig);
	}

	// 배포 환경 ("prod" 또는 "dev" 프로파일일 때 활성화)
	// 정석대로 보안 검사를 수행함 (코드가 없으면 Spring Boot 기본 자동설정이 돌지만, 명시적으로 적자면)
	@Bean
	@Profile({"prod", "dev"})
	public LettuceConnectionFactory prodRedisConnectionFactory() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration(host, port);

		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
			.useSsl() // SSL은 켜되, PeerVerification은 끄지 않음 (기본값: 검사함)
			.build();

		return new LettuceConnectionFactory(redisConfig, clientConfig);
	}
}
