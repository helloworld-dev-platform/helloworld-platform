package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자가 사전 테스트의 각 문제에 제출한 답안을 기록하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_PRE_TEST_ANSWER")
public class UserPreTestAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_pre_test_answer_id_seq_generator")
    @SequenceGenerator(name = "user_pre_test_answer_id_seq_generator", sequenceName = "user_pre_test_answer_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PRE_TEST_SESSION_ID", nullable = false)
    private UserPreTestSession userPreTestSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEMS_ID", nullable = false)
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHOICES_ID", nullable = false)
    private Choice choice;

    @Column(name = "ANSWERED_AT")
    private LocalDateTime answeredAt;

    @Column(name = "SUBMITTED_ANSWER", columnDefinition = "TEXT")
    private String submittedAnswer;
}
