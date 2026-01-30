package com.helloworld.backend_api.leveltest.strategy.impl;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.leveltest.dto.LevelTestGradeRequest;
import com.helloworld.backend_api.leveltest.strategy.GradingResult;
import com.helloworld.backend_api.leveltest.strategy.ProblemGrader;
import com.helloworld.backend_api.problem.domain.Solution;
import com.helloworld.backend_api.problem.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectiveGrader implements ProblemGrader {

  private final SolutionRepository solutionRepository;

  @Override
  public String getSupportedType() {
    return "SUBJECTIVE";
  }

  @Override
  public GradingResult grade(Long problemId, LevelTestGradeRequest request) {
    // 1. DB에서 정답 조회 (Solution 테이블)
    Solution solution = solutionRepository.findByProblemId(problemId)
        .orElseThrow(() -> new CustomException(ErrorCode.SOLUTION_NOT_FOUND));

    // 2. 사용자 입력값 가져오기 (null 방지)
    String userAnswer = request.getUserAnswer();
    if (userAnswer == null) {
      userAnswer = "";
    }

    // 3. 정답 비교 로직 (대소문자 무시 & 앞뒤 공백 제거)
    // DB 정답: "Object" / 유저 입력: " object " -> 정답 처리
    boolean isCorrect = solution.getContent().trim().equalsIgnoreCase(userAnswer.trim());

    // 4. 결과 반환
    return GradingResult.builder()
        .isCorrect(isCorrect)
        .selectedChoiceId(null) // 주관식은 선택지 ID 없음
        .submittedAnswer(userAnswer.trim()) // 공백 제거된 버전으로 저장
        .build();
  }
}
