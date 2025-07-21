package com.helloworld.backend_api.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); //내 서버가 응답을 할때 json을 자바스크립트에서 처리할 수 있게 할지 설정하는 것.
        config.addAllowedOriginPattern("*");  // 모든 IP의 응답을 허용
        config.addAllowedHeader("*");         // 모든 헤더의 응답을 허용
        config.addAllowedMethod("*");         // 모든 HTTP 메서드(post,get,patch등) 허용

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
