package com.helloworld.backend_api.pretest.strategy.impl;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.pretest.dto.PreTestGradeRequest;
import com.helloworld.backend_api.pretest.strategy.GradingResult;
import com.helloworld.backend_api.pretest.strategy.ProblemGrader;
import com.helloworld.backend_api.problem.domain.BlankSolution;
import com.helloworld.backend_api.problem.repository.BlankSolutionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FillInTheBlankGrader implements ProblemGrader {

  /**
   * 빈칸 채우기는 순서가 매우 중요 BlankSolutionRepository에서 OrderByBlankOrderAsc로 가져와서, 사용자가 입력한 리스트와 1:1로
   * 매칭해야함
   */

  private final BlankSolutionRepository blankSolutionRepository;

  @Override
  public String getSupportedType() {
    return "FILL_IN_THE_BLANK";
  }

  @Override
  public GradingResult grade(Long problemId, PreTestGradeRequest request) {
    // 1. DB에서 정답 목록 조회 (순서대로 정렬 필수)
    List<BlankSolution> solutions = blankSolutionRepository.findAllByProblemIdOrderByBlankOrderAsc(
        problemId);

    if (solutions.isEmpty()) {
      throw new CustomException(ErrorCode.SOLUTION_NOT_FOUND);
    }

    // 2. 사용자 입력 리스트 가져오기
    List<String> userAnswers = request.getUserAnswers();

    // 3. 채점 로직
    boolean isCorrect = true;

    // 3-1. 개수 불일치 시 오답 (빈칸은 3개인데 답을 2개만 보낸 경우 등)
    if (userAnswers == null || solutions.size() != userAnswers.size()) {
      isCorrect = false;
    } else {
      // 3-2. 순서대로 1:1 비교
      for (int i = 0; i < solutions.size(); i++) {
        String dbAnswer = solutions.get(i).getContent().trim();
        String inputAnswer = userAnswers.get(i).trim();

        if (!dbAnswer.equalsIgnoreCase(inputAnswer)) {
          isCorrect = false;
          break; // 하나라도 틀리면 오답 처리
        }
      }
    }

    // 4. DB 저장을 위해 리스트 -> 문자열 변환 (예: "답1,답2,답3")
    String combinedAnswer = "";
    if (userAnswers != null && !userAnswers.isEmpty()) {
      combinedAnswer = userAnswers.stream()
          .map(String::trim)
          .collect(Collectors.joining(","));
    }

    return GradingResult.builder()
        .isCorrect(isCorrect)
        .selectedChoiceId(null)
        .submittedAnswer(combinedAnswer) // 합쳐진 문자열 저장
        .build();
  }

}
