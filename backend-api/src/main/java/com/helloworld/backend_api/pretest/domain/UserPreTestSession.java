package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import com.helloworld.backend_api.user.domain.User;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자 개개인의 사전 테스트 응시 세션을 기록하는 엔티티 어떤 사용자가, 어떤 언어의 테스트를, 언제 시작하고 완료했는지 관리한다.
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_PRE_TEST_SESSION")
public class UserPreTestSession {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRE_TEST_LEVEL_ID", nullable = false)
  private PreTestLevel levelId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LEANING_LANGUAGE_ID", nullable = false)
  private LearningLanguage languageId;

  @Column(name = "TEST_STARTED_AT", nullable = false)
  private LocalDateTime testStartedAt;

  @Column(name = "TEST_COMPLEATED_AT")
  private LocalDateTime testCompletedAt;
}
