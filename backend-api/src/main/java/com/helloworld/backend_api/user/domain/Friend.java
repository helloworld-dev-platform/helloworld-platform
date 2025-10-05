package com.helloworld.backend_api.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 간의 친구 관계를 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "friend")
public class Friend {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 중복을 피하고 조회를 쉽게 하기 위해 보통 ID값이 작은 쪽을 A, 큰 쪽을 B에 저장하는 규칙을 적용합니다.
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_id_low", nullable = false)
  private User userA;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_id_high", nullable = false)
  private User userB;
}
