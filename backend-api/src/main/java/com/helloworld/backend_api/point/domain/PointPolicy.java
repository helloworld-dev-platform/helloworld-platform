package com.helloworld.backend_api.point.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 포인트 획득/차감 정책의 종류와 기준을 정의하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POINT_POLICY")
public class PointPolicy extends BaseTimeEntity {
    @Id
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "POINT_AMOUNT", nullable = false)
    private Integer pointAmount;

    @Column(name = "POINT_DESCRIPTION")
    private String pointDescription;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;
}
