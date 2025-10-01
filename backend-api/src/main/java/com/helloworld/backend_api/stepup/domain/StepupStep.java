package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Problem;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * '섹션'의 하위 단위인 '스텝'(학습 단계)을 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_STEP")
public class StepupStep extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stepup_step_id_seq_generator")
    @SequenceGenerator(name = "stepup_step_id_seq_generator", sequenceName = "stepup_step_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEPUP_SECTION_ID", nullable = false)
    private StepupSection stepupSection;

    @Column(name = "STEP_TITLE")
    private String stepTitle;

    @Column(name = "STEP_ORDER")
    private Integer stepOrder;

    @OneToMany(mappedBy = "stepupStep")
    private List<Problem> problems = new ArrayList<>();
}
