package com.helloworld.backend_api.achievement.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "badge")
public class Badge extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "badge_id_seq_generator")
    @SequenceGenerator(name = "badge_id_seq_generator", sequenceName = "badge_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "BADGE_NAME", nullable = false, length = 256)
    private String badgeName;

    @Column(name = "BADGE_DESC", length = 256)
    private String badgeDesc;

    @Column(name = "BADGE_IMG_URL", nullable = false, columnDefinition = "TEXT")
    private String badgeImgUrl;

    @Column(name = "BADGE_GET_COND", length = 256)
    private String badgeGetCond;
}
