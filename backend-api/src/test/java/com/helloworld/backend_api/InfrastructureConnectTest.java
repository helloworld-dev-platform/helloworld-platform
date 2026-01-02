package com.helloworld.backend_api;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.management.ThreadDumpEndpoint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import com.helloworld.backend_api.common.config.RedisConfig;

import io.awspring.cloud.s3.S3Template;
import io.awspring.cloud.sqs.operations.SqsTemplate;

/**
 * com.helloworld.backend_api.InfrastructureConnectTest
 *
 * @author      : User
 * @since       : 2026-01-02
 * @description :
 * ===============================================
 * DATE        AUTHOR          NOTE
 * ===============================================
 * 2026-01-02     User         최초 생성
 **/

@SpringBootTest
@Import(RedisConfig.class)
public class InfrastructureConnectTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private S3Template s3Template;

	@Autowired
	private SqsTemplate sqsTemplate;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucketName;

	@Value("${spring.cloud.aws.sqs.queue-url}")
	private String queueUrl;

	@Autowired
	private Environment env;

	@Test
	@DisplayName("0. 설정값 확인 (진실의 방)")
	void printConfig() {
		System.out.println("=========================================");
		System.out.println("현재 Redis Host: " + env.getProperty("spring.data.redis.host"));
		System.out.println("현재 Redis Port: " + env.getProperty("spring.data.redis.port"));
		System.out.println("현재 S3 Access Key: " + env.getProperty("spring.cloud.aws.credentials.access-key"));
		System.out.println("=========================================");
	}

	@Test
	@DisplayName("1. RDS 연결테스트(postgreSQL)")
	void testRdsConnection() throws Exception {
		try (Connection connection = dataSource.getConnection()){
			assertThat(connection.isValid(1)).isTrue();
			System.out.println("RDS 연결 성공: "+connection.getMetaData().getURL());
		}

		//쿼리 실행 테스트
		Integer result = jdbcTemplate.queryForObject("select 1", Integer.class);
		assertThat(result).isEqualTo(1);
	}

	@Test
	@DisplayName("2. Valkey 연결테스트")
	void testRedisConnection() throws Exception {
		//Given
		String key = "test-env-check";
		String value = "connected";

		//When
		redisTemplate.opsForValue().set(key, value);
		String fetchedValue = redisTemplate.opsForValue().get(key);

		//Then
		assertThat(fetchedValue).isEqualTo(value);
		redisTemplate.delete(key); //테스트 데이터 삭제
		System.out.println("Valkey 연결 성공:");

	}

	@Test
	@DisplayName("3. S3 버킷 조회 테스트")
	void testS3Connection() throws Exception {
		//When
		boolean bucketExists = s3Template.bucketExists(bucketName);

		//Then
		assertThat(bucketExists).isTrue(); //버킷이 존재하는지 확인
		System.out.println("S3 연결 성공 (버킷 이름: " + bucketName + ")");
	}

	@Test
	@DisplayName("4. SQS 메시지 전송 테스트")
	void testSqsConnection() throws Exception {
		//When
		var result = sqsTemplate.send(to -> to
			.queue(queueUrl)
			.payload("Integration Test Message"));

		// Then
		assertThat(result).isNotNull();
		System.out.println("SQS 연결 성공 (Message ID: " + result.messageId() + ")");

	}
}
