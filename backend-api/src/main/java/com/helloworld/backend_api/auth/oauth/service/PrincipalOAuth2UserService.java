package com.helloworld.backend_api.auth.oauth.service;

import com.helloworld.backend_api.auth.jwt.JwtTokenProvider;
import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.auth.oauth.userinfo.GoogleUserInfo;
import com.helloworld.backend_api.auth.oauth.userinfo.OAuth2UserInfo;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final HttpServletResponse response;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oauth2User = super.loadUser(userRequest);
    log.info(">>>>>>>>>>>>>>>>>  getAttributes={}", oauth2User.getAttributes());

    //회원가입 강제 진행
    OAuth2UserInfo oAuth2UserInfo = null;
    if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
      oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
    } else {
      log.info(">>>>>>>현재 구글만 지원함.");
    }

    String provider = oAuth2UserInfo.getProvider();
    String providerId = oAuth2UserInfo.getProviderId();
    String username = provider + "_" + providerId;
    String email = oAuth2UserInfo.getEmail();
    String role = "USER";
    User user = userRepository.findByUserName(username);

    if (user == null) {
      user = user.builder()
          .userEmail(email)
          .userName(username)
          .userRole(role)
          .build();
    }
    userRepository.save(user);
    return new PrincipalDetails(user, oauth2User.getAttributes());
  }

}
