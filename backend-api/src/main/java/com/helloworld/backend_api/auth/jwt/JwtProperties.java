package com.helloworld.backend_api.auth.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.properties에 정의된 jwt 설정값 자동 바인딩
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@Schema(description = "JWT설정 정보")
public class JwtProperties {

  @Schema(description = "JWT 서명용 시크릿 키")
  private String secretKey;
  @Schema(description = "Access Token 만료시간(밀리초)", example = "3600000")
  private long expirationTime;
}
