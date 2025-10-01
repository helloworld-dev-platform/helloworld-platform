package com.helloworld.backend_api.problem.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 주관식 문제(Problem)의 정답을 나타내는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="SOLUTIONS")
public class Solution extends BaseTimeEntity {
    @Id
    // @GeneratedValue: 기본 키 값의 자동 생성 전략을 설정합니다.
    // strategy = GenerationType.SEQUENCE: 데이터베이스 시퀀스를 통해 키 값을 생성합니다. 성능 및 배치 처리에 유리합니다.
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solutions_id_seq_generator")
    // @SequenceGenerator: 사용할 시퀀스에 대한 상세 정보를 정의합니다.
    // name: 시퀀스 생성기의 논리적 이름. @GeneratedValue의 generator 속성과 일치해야 합니다.
    // sequenceName: 데이터베이스에 생성될 실제 시퀀스의 이름입니다.
    // allocationSize: 한 번에 할당할 시퀀스 수. 성능 최적화를 위해 사용됩니다.
    @SequenceGenerator(name = "solutions_id_seq_generator", sequenceName = "solutions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false) // @Column: 필드와 컬럼을 매핑합니다. DDL의 COMMENT를 참고하여 nullable 등 제약조건을 설정합니다.
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEMS_ID", nullable = false)
    private Problem problem;

    @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "CORRECT_DESCRIPTION", columnDefinition = "TEXT")
    private String correctDescription;
}
