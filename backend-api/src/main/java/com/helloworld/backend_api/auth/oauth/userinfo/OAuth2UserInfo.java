package com.helloworld.backend_api.auth.oauth.userinfo;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}
