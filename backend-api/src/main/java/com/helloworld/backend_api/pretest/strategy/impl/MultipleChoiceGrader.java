package com.helloworld.backend_api.pretest.strategy.impl;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.pretest.dto.PreTestGradeRequest;
import com.helloworld.backend_api.pretest.strategy.GradingResult;
import com.helloworld.backend_api.pretest.strategy.ProblemGrader;
import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.repository.ChoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MultipleChoiceGrader implements ProblemGrader {

  private final ChoiceRepository choiceRepository;

  @Override
  public String getSupportedType() {
    return "MULTIPLE_CHOICE";
  }

  @Override
  public GradingResult grade(Long problemId, PreTestGradeRequest request) {
    Choice choice = choiceRepository.findByProblemIdAndContent(problemId, request.getUserAnswer())
        .orElseThrow(() -> new CustomException(ErrorCode.CHOICE_NOT_FOUND));

    return GradingResult.builder()
        .isCorrect(choice.isCorrect())
        .selectedChoiceId(choice.getId())
        .submittedAnswer(choice.getContent())
        .build();
  }
}
