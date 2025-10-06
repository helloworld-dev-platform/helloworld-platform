package com.helloworld.backend_api.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

/**
 * 사용자 간의 친구 신청 내역을 관리하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "friend_request")
public class FriendRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "REQUESTER_ID", nullable = false)
  private User requester;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "RECEIVER_ID", nullable = false)
  private User receiver;

  @Enumerated(EnumType.STRING)
  @Column(name = "FRIEND_REQ_STATUS")
  private FriendRequestStatus status;

  @Column(name = "REQUESTED_AT", nullable = false)
  private LocalDateTime requestedAt;

  @Column(name = "RESPONDED_AT", nullable = false)
  private LocalDateTime respondedAt;
}
