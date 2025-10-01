package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.achievement.domain.Tier;
import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User가 특정 시즌에 어떤 Tier에 속했는지 연결하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_tier")
public class UserTier extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_tier_id_seq_generator")
    @SequenceGenerator(name = "user_tier_id_seq_generator", sequenceName = "user_tier_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TIER_ID", nullable = false)
    private Tier tier;

    @Column(name = "SEASON_INFO", length = 50)
    private String seasonInfo;

}
