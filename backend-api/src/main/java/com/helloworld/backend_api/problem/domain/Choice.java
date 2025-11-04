package com.helloworld.backend_api.problem.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
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
 * 객관식 문제(Problem)의 각 선택지를 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "CHOICE")
public class Choice extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROBLEM_ID", nullable = false)
  private Problem problemId;

  @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
  private String content;

  @Column(name = "IS_CORRECT", nullable = false)
  private boolean isCorrect;
}
