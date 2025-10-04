package com.helloworld.backend_api.user.repository;

import com.helloworld.backend_api.user.domain.UserOauthCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOauthCredentialRepository extends JpaRepository<UserOauthCredential, Long> {
    Optional<UserOauthCredential> findByProviderAndProviderId(String provider, String providerId);
}
