package com.helloworld.backend_api.pretest.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_PRE_TEST_RESULT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPreTestResult {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  // 최종 확정된 레벨 ID
  @Column(name = "PRE_TEST_LEVEL_ID", nullable = false)
  private Long preTestLevelId;

  @Column(name = "LEANING_LANGUAGE_ID", nullable = false)
  private Long learningLanguageId;

  @Column(name = "COMPLETED_AT")
  private LocalDateTime completedAt;

  @Column(name = "CREATED_AT", nullable = false)
  private LocalDateTime createdAt;

  @Column(name = "UPDATED_AT", nullable = false)
  private LocalDateTime updatedAt;

  @Builder
  public UserPreTestResult(Long userId, Long preTestLevelId, Long learningLanguageId) {
    this.userId = userId;
    this.preTestLevelId = preTestLevelId;
    this.learningLanguageId = learningLanguageId;
    this.completedAt = LocalDateTime.now();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }
}
