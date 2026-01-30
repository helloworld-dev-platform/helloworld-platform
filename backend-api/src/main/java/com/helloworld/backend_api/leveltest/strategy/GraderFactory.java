package com.helloworld.backend_api.leveltest.strategy;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class GraderFactory {

  private final Map<String, ProblemGrader> graderMap;

  public GraderFactory(List<ProblemGrader> graders) {
    this.graderMap = graders.stream()
        .collect(Collectors.toMap(ProblemGrader::getSupportedType, Function.identity()));
  }

  public ProblemGrader getGrader(String problemType) {
    ProblemGrader grader = graderMap.get(problemType);
    if (grader == null) {
      throw new CustomException(ErrorCode.UNSUPPORTED_PROBLEM_TYPE);
    }
    return grader;
  }
}
