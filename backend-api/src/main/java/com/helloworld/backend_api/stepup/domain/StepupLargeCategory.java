package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.LearningLanguage;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Course -> Section -> Step으로 이어지는 계층구조 '스텝업 코스'의 최상위 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_LARGE_CATEGORY")
public class StepupLargeCategory extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "LEARNING_LANGUAGE_ID", nullable = false)
  private LearningLanguage languageId;

  @Column(name = "LARGE_CATEGORY_NAME")
  private String largeCategoryName;

  @Enumerated(EnumType.STRING)
  @Column(name = "difficulty", nullable = false)
  private Difficulty difficulty;

  @OneToMany(mappedBy = "largeCategoryId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StepupMediumCategory> sections = new ArrayList<>();
}
