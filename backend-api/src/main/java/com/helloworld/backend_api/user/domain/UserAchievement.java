package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.achievement.domain.Achievement;
import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * User의 업적 달성 진행도를 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_achievement")
public class UserAchievement extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_achievement_id_seq_generator")
    @SequenceGenerator(name = "user_achievement_id_seq_generator", sequenceName = "user_achievement_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACHIEVE_ID", nullable = false)
    private Achievement achievement;

    @Column(name = "NOW_PROGRESS_LEVEL", nullable = false)
    private Integer nowProgressLevel;
}
