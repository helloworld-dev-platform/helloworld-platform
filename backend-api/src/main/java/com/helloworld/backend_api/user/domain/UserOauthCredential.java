package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자의 소셜 로그인(OAuth) 인증 정보를 관리하는 엔티티입니다.
 * 한 명의 사용자는 여러 소셜 계정을 연동할 수 있습니다. (예: 구글, 카카오)
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_oauth_credential")
public class UserOauthCredential extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_oauth_credential_id_seq_generator")
    @SequenceGenerator(name = "user_oauth_credential_id_seq_generator", sequenceName = "user_oauth_credential_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "PROVIDER", nullable = false, length = 10)
    private String provider;

    @Column(name = "PROVIDER_ID", nullable = false, length = 100)
    private String providerId;

    @Builder
    public UserOauthCredential(User user, String provider, String providerId) {
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
    }

}
