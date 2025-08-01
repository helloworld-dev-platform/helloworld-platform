package com.helloworld.backend_api.auth.model;

import com.helloworld.backend_api.user.domain.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private Users user;

    private Map<String, Object> attributes;
    public PrincipalDetails(Users user) {
        this.user = user;
    }

    public PrincipalDetails(Users user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority)() -> user.getRole());

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
