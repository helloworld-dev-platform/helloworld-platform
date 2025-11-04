package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Problem;
import io.swagger.v3.oas.annotations.media.Schema;
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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Schema(description = "공부세션기록 Entity")
@Table(name = "study_session")
@Getter
public class StudySession extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROBLEM_ID")
  private Problem problem;

  @Column(name = "DOMAIN_TYPE", nullable = false)
  private String domainType;

  @Column(name = "START_TIME", nullable = false)
  private LocalDateTime startTime;

  @Column(name = "END_TIME", nullable = false)
  private LocalDateTime endTime;

  @Column(name = "DURATION_SECOND", nullable = false)
  private int durationSecond;

  @Builder
  public StudySession(Long id, User user, Problem problem, String domainType, int durationSecond) {
    this.id = id;
    this.user = user;
    this.problem = problem;
    this.domainType = domainType;
    this.durationSecond = durationSecond;
    this.startTime = LocalDateTime.now();
  }
}
