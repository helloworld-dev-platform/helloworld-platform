package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Problem;
import com.helloworld.backend_api.user.domain.User;
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
 * 사용자의 스텝업 코스 문제 풀이 제출이력을 기록하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_COURSE_PROGRESS")
public class UserCourseProgress extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PROBLEM_ID", nullable = false)
  private Problem problemId;

  @Enumerated(EnumType.STRING)
  @Column(name = "STEPUP_STATUS")
  private StepupStatus status;

  @Column(name = "TEMP_ANSWER", columnDefinition = "TEXT")
  private String tempAnswer;


  @Column(name = "COMPLETED_AT")
  private LocalDateTime completedAt;
}
