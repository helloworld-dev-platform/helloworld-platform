package com.helloworld.backend_api.pretest.domain;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import com.helloworld.backend_api.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 사용자 개개인의 사전 테스트 응시 세션을 기록하는 엔티티
 * 어떤 사용자가, 어떤 언어의 테스트를, 언제 시작하고 완료했는지 관리한다.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USER_PRE_TEST_SESSION")
public class UserPreTestSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_pre_test_session_id_seq_generator")
    @SequenceGenerator(name = "user_pre_test_session_id_seq_generator", sequenceName = "user_pre_test_session_id_seq", allocationSize = 1)
    private Long id;

    //사용자가 여러번 사전레벨테스트를 응시할 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRE_TEST_LEVEL_ID", nullable = false)
    private PreTestLevel preTestLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEANING_LANGUAGE_ID", nullable = false)
    private LearningLanguage learningLanguage;

    @Column(name = "TEST_STARTED_AT", nullable = false)
    private LocalDateTime testStartedAt;

    @Column(name = "TEST_COMPLEATED_AT")
    private LocalDateTime testCompletedAt;
}
