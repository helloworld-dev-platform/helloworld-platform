package com.helloworld.backend_api.pretest.strategy;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GradingResult {

  private boolean isCorrect;
  private Long selectedChoiceId;
  private String submittedAnswer;
}
