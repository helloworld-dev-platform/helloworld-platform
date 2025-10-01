package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * '스텝업 코스'의 하위 단위인 '섹션'을 나타내는 엔티티
 * 하나의 섹션은 여러 개의 스텝(Step)으로 구성됨
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_SECTION")
public class StepupSection extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stepup_section_id_seq_generator")
    @SequenceGenerator(name = "stepup_section_id_seq_generator", sequenceName = "stepup_section_id_seq", allocationSize = 1)
    private Long id;

    // StepupCourse 엔티티와 다대일(N:1) 관계. 연관관계의 주인입니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEPUP_COURSE_ID", nullable = false)
    private StepupCourse stepupCourse;

    @Column(name = "SECTION_NAME", nullable = false)
    private String sectionName;

    @Column(name = "SECTION_ORDER", nullable = false)
    private Integer sectionOrder;

    // StepupStep 엔티티와 일대다(1:N) 관계. 하나의 섹션은 여러 스텝을 가집니다.
    @OneToMany(mappedBy = "stepupSection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StepupStep> steps = new ArrayList<>();
}
