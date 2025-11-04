package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Problem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * '섹션'의 하위 단위인 '스텝'(학습 단계)을 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_SMALL_CATEGORY")
public class StepupSmallCategory extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STEPUP_MEDIUM_CATEGORY_ID", nullable = false)
  private StepupMediumCategory mediumCategoryId;

  @Column(name = "SMALL_CATEGORY_TITLE")
  private String smallCategoryTitle;

  @Column(name = "SMALL_CATEGORY_ORDER")
  private Integer SmallCategoryOrder;

  @OneToMany(mappedBy = "smallCategoryId")
  private List<Problem> problems = new ArrayList<>();
}
