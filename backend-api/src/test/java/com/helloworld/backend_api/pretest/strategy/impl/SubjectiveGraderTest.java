package com.helloworld.backend_api.pretest.strategy.impl;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.pretest.dto.PreTestGradeRequest;
import com.helloworld.backend_api.pretest.strategy.GradingResult;
import com.helloworld.backend_api.problem.domain.Solution;
import com.helloworld.backend_api.problem.repository.SolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubjectiveGraderTest {

    @Mock
    private SolutionRepository solutionRepository;

    @InjectMocks
    private SubjectiveGrader subjectiveGrader;

    private Solution solution;

    @BeforeEach
    void setUp() {
        solution = new Solution();
        // Using reflection to set private fields for test setup
        try {
            java.lang.reflect.Field idField = Solution.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(solution, 1L);

            java.lang.reflect.Field contentField = Solution.class.getDeclaredField("content");
            contentField.setAccessible(true);
            contentField.set(solution, "Object");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("정답일 경우")
    void grade_CorrectAnswer() {
        // given
        PreTestGradeRequest request = new PreTestGradeRequest(" object ");
        when(solutionRepository.findByProblemId(1L)).thenReturn(Optional.of(solution));

        // when
        GradingResult result = subjectiveGrader.grade(1L, request);

        // then
        assertThat(result.isCorrect()).isTrue();
        assertThat(result.getSubmittedAnswer()).isEqualTo("object");
    }

    @Test
    @DisplayName("오답일 경우")
    void grade_IncorrectAnswer() {
        // given
        PreTestGradeRequest request = new PreTestGradeRequest("class");
        when(solutionRepository.findByProblemId(1L)).thenReturn(Optional.of(solution));

        // when
        GradingResult result = subjectiveGrader.grade(1L, request);

        // then
        assertThat(result.isCorrect()).isFalse();
        assertThat(result.getSubmittedAnswer()).isEqualTo("class");
    }

    @Test
    @DisplayName("정답을 찾을 수 없는 경우")
    void grade_SolutionNotFound() {
        // given
        PreTestGradeRequest request = new PreTestGradeRequest("anything");
        when(solutionRepository.findByProblemId(anyLong())).thenReturn(Optional.empty());

        // when & then
        assertThrows(CustomException.class, () -> subjectiveGrader.grade(1L, request));
    }

    @Test
    @DisplayName("사용자 답변이 null일 경우")
    void grade_NullUserAnswer() {
        // given
        PreTestGradeRequest request = new PreTestGradeRequest(null);
        when(solutionRepository.findByProblemId(1L)).thenReturn(Optional.of(solution));

        // when
        GradingResult result = subjectiveGrader.grade(1L, request);

        // then
        assertThat(result.isCorrect()).isFalse();
        assertThat(result.getSubmittedAnswer()).isEqualTo("");
    }
}
