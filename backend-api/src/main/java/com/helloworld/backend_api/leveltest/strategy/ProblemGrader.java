package com.helloworld.backend_api.leveltest.strategy;

import com.helloworld.backend_api.leveltest.dto.LevelTestGradeRequest;

public interface ProblemGrader {

  String getSupportedType();

  GradingResult grade(Long problemId, LevelTestGradeRequest request);
}
