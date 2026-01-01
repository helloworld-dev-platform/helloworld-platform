package com.helloworld.backend_api.pretest.strategy;

import com.helloworld.backend_api.pretest.dto.PreTestGradeRequest;

public interface ProblemGrader {

  String getSupportedType();

  GradingResult grade(Long problemId, PreTestGradeRequest request);
}
