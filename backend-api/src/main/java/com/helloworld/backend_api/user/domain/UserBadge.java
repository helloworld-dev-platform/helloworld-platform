package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.achievement.domain.Badge;
import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User가 어떤 Badge를 언제 획득했는지 연결하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_badge")
public class UserBadge extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_badge_id_seq_generator")
    @SequenceGenerator(name = "user_badge_id_seq_generator", sequenceName = "user_badge_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BADGE_ID", nullable = false)
    private Badge badge;

    @Column(name = "BADGE_GET_AT", nullable = false)
    private LocalDateTime badgeGetAt;

    @Column(name = "IS_MAIN_BADGE", nullable = false)
    private boolean isMainBadge;
}
