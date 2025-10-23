package com.helloworld.backend_api.oauth.userinfo;

public interface OAuth2UserInfo {

  String getProvider();

  String getProviderId();

  String getEmail();

  String getName();
}
