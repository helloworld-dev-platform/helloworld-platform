package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.common.domain.BaseTimeEntity;
import com.helloworld.backend_api.problem.domain.Problem;
import com.helloworld.backend_api.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자의 스텝업 코스 문제 풀이 진행 상황을 기록하는 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_COURSE_PROGRESS")
public class UserCourseProgress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_course_progress_id_seq_generator")
    @SequenceGenerator(name = "user_course_progress_id_seq_generator", sequenceName = "user_course_progress_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROBLEM_ID", nullable = false)
    private Problem problem;

    @Column(name = "STEPUP_STATUS", nullable = false)
    private String stepupStatus;

    @Column(name = "TEMP_ANSWER", columnDefinition = "TEXT")
    private String tempAnswer;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;
}
