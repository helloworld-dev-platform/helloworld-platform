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
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 빈칸채우기 문제(Problem)의 정답을 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "BLANK_SOLUTION",
    uniqueConstraints = { // 👇 이 부분을 추가하세요.
        @UniqueConstraint(
            name = "uq_problem_blank_order",
            columnNames = {"PROBLEM_ID", "BLANK_ORDER"}
        )
    })
public class BlankSolution extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROBLEM_ID", nullable = false)
  private Problem problemId;

  @Column(name = "BLANK_ORDER", nullable = false, columnDefinition = "TEXT")
  private int blankOrder;

  @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
  private String content;
}
