package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.CascadeType;
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
 * '스텝업 코스'의 하위 단위인 '섹션'을 나타내는 엔티티 하나의 섹션은 여러 개의 스텝(Step)으로 구성됨
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_MEDIUM_CATEGORY")
public class StepupMediumCategory extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // StepupCourse 엔티티와 다대일(N:1) 관계. 연관관계의 주인입니다.
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "STEPUP_LARGE_CATEGORY_ID", nullable = false)
  private StepupLargeCategory largeCategoryId;

  @Column(name = "MEDIUM_CATEGORY_NAME", nullable = false)
  private String mediumCategoryName;

  @Column(name = "MEDIUM_CATEGORY_ORDER", nullable = false)
  private Integer mediumCategoryOrder;

  // StepupStep 엔티티와 일대다(1:N) 관계. 하나의 섹션은 여러 스텝을 가집니다.
  @OneToMany(mappedBy = "mediumCategoryId", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<StepupSmallCategory> steps = new ArrayList<>();
}
