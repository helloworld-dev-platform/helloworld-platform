package com.helloworld.backend_api.user.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.pretest.domain.PreTestLevel;
import com.helloworld.backend_api.problem.domain.LearningLanguage;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@NoArgsConstructor
@Schema(description = "사용자별사전레벨테스트결과 Entity")
@Table(name = "USER_PRE_TEST_RESULT")
@Getter
public class UserPretestResult extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JdbcTypeCode(SqlTypes.BIGINT)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LEARNING_LANGUAGE_ID", nullable = false)
  private LearningLanguage languageId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRE_TEST_LEVEL_ID", nullable = false)
  private PreTestLevel levelId;

  @Column(name = "COMPLETED_AT")
  private LocalDateTime completedAt;

  @Builder
  public UserPretestResult(Long id, LearningLanguage languageId, User user,
      PreTestLevel levelId, LocalDateTime completedAt) {
    this.id = id;
    this.languageId = languageId;
    this.user = user;
    this.levelId = levelId;
    this.completedAt = completedAt;
  }
}
