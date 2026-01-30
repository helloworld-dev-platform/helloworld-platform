package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.Problem;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * Problem - QueryDSL용 인터페이스
 */
public interface ProblemRepositoryQuery {

  List<Problem> findLevelTestProblems(@NotNull Long languageId, Difficulty difficulty);
}
