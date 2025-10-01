package com.helloworld.backend_api.problem.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.stepup.domain.StepupStep;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 시스템의 '문제'를 나타내는 핵심 엔티티.
 * 스텝업 코스, 사전 테스트, 코딩 테스트에서 사용됨.
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PROBLEMS")
public class Problem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "problems_id_seq_generator")
    @SequenceGenerator(name = "problems_id_seq_generator", sequenceName = "problems_id_seq", allocationSize = 1)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEARNING_LANGUAGE_ID")
    private LearningLanguage learningLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STEPUP_STEP_ID")
    private StepupStep stepupStep;

    @Column(name = "PROBLEM_TYPE", nullable = false)
    private String problemType;

    @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "DOMAIN_TYPE", nullable = false)
    private String domainType;

    @Column(name = "DIFFICULTY", nullable = false)
    private String difficulty;
}
