package com.helloworld.backend_api.pretest.dto;

import com.helloworld.backend_api.problem.domain.Difficulty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PreTestDifficultyGroup {
  BEGINNER(Difficulty.LEVEL_1),
  INTERMEDIATE(Difficulty.LEVEL_4),
  ADVANCED(Difficulty.LEVEL_8);

  private final Difficulty startDifficulty;
}
