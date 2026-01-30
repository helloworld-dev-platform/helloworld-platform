package com.helloworld.backend_api.common.config;

import com.helloworld.backend_api.auth.OAuth2SuccessHandler;
import com.helloworld.backend_api.oauth.service.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
//    Map<String, PasswordEncoder> encoders = new HashMap<>();
//    encoders.put("bcrypt", new BCryptPasswordEncoder());
//    encoders.put("noop", NoOpPasswordEncoder.getInstance());

    //테스트를 위해 임시로 평문

    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Autowired
  private PrincipalOAuth2UserService oAuth2UserService;

  @Autowired
  private OAuth2SuccessHandler oAuth2SuccessHandler;

  /**
   * csrf 비활성화 추후, 사용자 추가정보 받게되면 수정필요함.
   *
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http,
      AuthenticationConfiguration authConfig) throws Exception {
    //로그인 검증을 위한 AuthenticationManager획득
    AuthenticationManager authManager = authConfig.getAuthenticationManager();

    //로그인 처리용 필터

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/v3/api-docs.yaml",
                "/swagger-resources/**",
                "/webjars/**",
                "/",
                "/dev/**",
                "/login/oauth2/code/google",
                "/auth/google/login",
                "/oauth2/**",
                "/auth/login",
                "/health",
                "/**"
            ).permitAll()
            .anyRequest().authenticated()
        );
        /*.oauth2Login(oauth2 -> oauth2
            .userInfoEndpoint(userInfo -> userInfo
                .userService(oAuth2UserService)
            )
            .successHandler(oAuth2SuccessHandler)
        );*/

    return http.build();
  }
}
