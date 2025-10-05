package com.helloworld.backend_api.pretest.domain;

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
@Table(name = "PRE_TEST_LEVEL")
public class PreTestLevel extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "PRE_TEST_LEVEL_NAME", nullable = false)
  private String preTestLevelName;

  @Column(name = "PRE_TEST_END_MSG", columnDefinition = "TEXT")
  private String preTestEndMsg;
}
