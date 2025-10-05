package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.domain.Problem;
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

/**
 * 사용자가 사전 테스트의 각 문제에 제출한 답안을 기록하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_PRE_TEST_ANSWER")
public class UserPreTestAnswer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
