package com.helloworld.backend_api.achievement.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tier")
public class Tier extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tier_id_seq_generator")
    @SequenceGenerator(name = "tier_id_seq_generator", sequenceName = "tier_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "TIER_NAME", nullable = false, length = 50)
    private String tierName;

    @Column(name = "TIER_MIN_PERCENT", nullable = false)
    private Double tierMinPercent;

    @Column(name = "TIER_MAX_PERCENT", nullable = false)
    private Double tierMaxPercent;
}
