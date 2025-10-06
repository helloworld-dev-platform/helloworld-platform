package com.helloworld.backend_api.auth.oauth.service;

import static com.helloworld.backend_api.user.domain.UserStatus.ACTIVE;

import com.helloworld.backend_api.auth.model.PrincipalDetails;
import com.helloworld.backend_api.auth.oauth.userinfo.GoogleUserInfo;
import com.helloworld.backend_api.auth.oauth.userinfo.OAuth2UserInfo;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.domain.UserOauthCredential;
import com.helloworld.backend_api.user.repository.UserOauthCredentialRepository;
import com.helloworld.backend_api.user.repository.UserRepository;
import java.util.Optional;
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
  private final UserOauthCredentialRepository userOauthCredentialRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    // 1. OAuth2User 원본 데이터 로드
    OAuth2User oauth2User = super.loadUser(userRequest);
    log.info(">>>>>>>>>>>>>>>>>  getAttributes={}", oauth2User.getAttributes());

    // 2. OAuth 공급자 정보 추출 및 유효성 검사
    OAuth2UserInfo oAuth2UserInfo = extractUserInfo(userRequest, oauth2User);

    // 3. 사용자 연동 및 로그인 처리 로직 실행
    User user = processUserRegistrationOrLogin(oAuth2UserInfo);

    // 4. PrincipalDetails 반환 (인증 완료)
    return new PrincipalDetails(user, oauth2User.getAttributes());
  }


  /**
   * OAuth2UserRequest에서 UserInfo를 추출하고 지원되는 Provider인지 검증
   */
  private OAuth2UserInfo extractUserInfo(OAuth2UserRequest userRequest, OAuth2User oauth2User) {
    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    if (registrationId.equals("google")) {
      return new GoogleUserInfo(oauth2User.getAttributes());
    } else {
      log.info(">>>>>>>현재 구글만 지원함. (ID: {})", registrationId);
      return null;
    }
  }

  /**
   * 추출된 UserInfo를 바탕으로 사용자 등록 또는 로그인 처리
   */
  private User processUserRegistrationOrLogin(OAuth2UserInfo userInfo) {
    String provider = userInfo.getProvider();
    String providerId = userInfo.getProviderId();
    String username = provider + "_" + providerId;
    String email = userInfo.getEmail();
    String role = "USER";

    // 1. 기존 연동 정보 확인 (소셜 로그인 처리)
    Optional<UserOauthCredential> credentialOptional =
        userOauthCredentialRepository.findByProviderAndProviderId(provider, providerId);

    if (credentialOptional.isPresent()) {
      // 연동 정보가 있으면 기존 사용자 반환
      return credentialOptional.get().getUser();
    }

    // 2. 연동 정보가 없으면, User를 찾거나 새로 생성
    User user = findOrCreateUser(username, email, role);

    // 3. UserOauthCredential 엔티티 저장 (연동 정보 추가)
    saveOauthCredential(user, provider, providerId);

    return user;
  }

  /**
   * User 테이블에서 사용자를 찾거나, 없으면 새로 생성합니다.
   */
  private User findOrCreateUser(String username, String email, String role) {
    User existingUser = userRepository.findByUserName(username);

    if (existingUser == null) {
      // 신규 User 엔티티 생성 및 저장 (회원가입)
      User newUser = User.builder()
          .userEmail(email)
          .userName(username)
          .userRole(role)
          .totalPoint(0)
          .status(ACTIVE)
          .build();
      return userRepository.save(newUser); // 저장하고 반환 (PK 생성됨)
    } else {
      // 기존 User 반환
      return existingUser;
    }
  }

  /**
   * 새로운 OAuth 연동 정보를 DB에 저장합니다.
   */
  private void saveOauthCredential(User user, String provider, String providerId) {
    UserOauthCredential credential = UserOauthCredential.builder()
        .user(user)
        .provider(provider)
        .providerId(providerId)
        .build();
    userOauthCredentialRepository.save(credential);
  }

}
