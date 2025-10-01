package com.helloworld.backend_api.problem.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 학습 대상 프로그래밍 언어를 정의하는 엔티티 (예: Python, Java)
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LEARNING_LANGUAGE")
public class LearningLanguage extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "learning_language_id_seq_generator")
    @SequenceGenerator(name = "learning_language_id_seq_generator", sequenceName = "learning_language_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "LANGUAGE_NAME", nullable = false)
    private String languageName;
}
