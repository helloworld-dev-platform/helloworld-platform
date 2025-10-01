package com.helloworld.backend_api.stepup.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.LearningLanguage;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Course -> Section -> Step으로 이어지는 계층구조
 * '스텝업 코스'의 최상위 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "STEPUP_COURSE")
public class StepupCourse extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stepup_course_id_seq_generator")
    @SequenceGenerator(name = "stepup_course_id_seq_generator", sequenceName = "stepup_course_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEANING_LANGUAGE_ID", nullable = false)
    private LearningLanguage learningLanguage;

    @Column(name = "COURSE_NAME")
    private String courseName;

    @OneToMany(mappedBy = "stepupCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StepupSection> sections = new ArrayList<>();
}
