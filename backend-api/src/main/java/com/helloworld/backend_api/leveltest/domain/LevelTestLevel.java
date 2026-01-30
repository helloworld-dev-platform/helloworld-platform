package com.helloworld.backend_api.leveltest.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LEVEL_TEST_LEVEL")
public class LevelTestLevel extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "LEVEL_TEST_LEVEL_NAME", nullable = false)
  private String levelTestLevelName;

  @Column(name = "LEVEL_TEST_END_MSG", columnDefinition = "TEXT")
  private String levelTestEndMsg;
}
