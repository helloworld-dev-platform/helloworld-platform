package com.helloworld.backend_api.problem.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.stepup.domain.StepupStep;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템의 '문제'를 나타내는 핵심 엔티티. 스텝업 코스, 사전 테스트, 코딩 테스트에서 사용됨.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PROBLEM")
public class Problem extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LEARNING_LANGUAGE_ID")
  private LearningLanguage learningLanguage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STEPUP_STEP_ID")
  private StepupStep stepupStep;

  @Enumerated(EnumType.STRING)
  private ProblemType problemType;

  @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(name = "DOMAIN_TYPE", nullable = false)
  private String domainType;

  @Enumerated(EnumType.STRING)
  @Column(name = "difficulty", nullable = false)
  private Difficulty difficulty;
}
