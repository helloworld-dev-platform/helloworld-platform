package com.helloworld.backend_api.point.domain;

import com.helloworld.backend_api.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

/**
 * 사용자의 모든 포인트 변동내역 기록 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POINT_HISTORY")
public class PointHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "POINT_POLICY_ID", nullable = false)
  private PointPolicy pointPolicyId;

  @Column(name = "POINT_AMOUNT", nullable = false)
  private Integer pointAmount;

  @Column(name = "POINT_DESCRIPTION")
  private String pointDescription;

  @CreationTimestamp
  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
