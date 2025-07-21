package com.helloworld.backend_api.auth.oauth.userinfo;

import com.helloworld.backend_api.auth.oauth.userinfo.OAuth2UserInfo;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String)attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String)attributes.get("email");
    }

    @Override
    public String getName() {
        return (String)attributes.get("name");
    }
}
