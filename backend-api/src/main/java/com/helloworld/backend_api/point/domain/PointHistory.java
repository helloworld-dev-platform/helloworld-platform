package com.helloworld.backend_api.point.domain;

import com.helloworld.backend_api.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 사용자의 모든 포인트 변동내역 기록 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POINT_HISTORY")
public class PointHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_history_id_seq_generator")
    @SequenceGenerator(name = "point_history_id_seq_generator", sequenceName = "point_history_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSACTION_TYPE", nullable = false)
    private PointPolicy transactionType;

    @Column(name = "POINT_AMOUNT", nullable = false)
    private Integer pointAmount;

    @Column(name = "POINT_DESCRIPTION")
    private String pointDescription;

    @CreationTimestamp
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
