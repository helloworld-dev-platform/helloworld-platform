package com.helloworld.backend_api.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 간의 친구 신청 내역을 관리하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "friend_request")
public class FriendRequest{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "friend_request_id_seq_generator")
    @SequenceGenerator(name = "friend_request_id_seq_generator", sequenceName = "friend_request_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", nullable = false)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVER_ID", nullable = false)
    private User receiver;

    @Column(name = "FRIEND_REQ_STAT_CD", nullable = false, length = 10)
    private String friendRequestStatus;

    @Column(name = "REQUESTED_AT", nullable = false)
    private LocalDateTime requestedAt;

    @Column(name = "RESPONDED_AT", nullable = false)
    private LocalDateTime respondedAt;
}
