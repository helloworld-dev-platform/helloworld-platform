package com.helloworld.backend_api.leveltest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사전 레벨 테스트 시작 요청 DTO")
public class LevelTestStartRequest {

  @NotNull
  @Schema(description = "학습 언어 ID (1: Python, 2: Java, 3: JS)", example = "1")
  private Long languageId;

  @NotNull
  @Schema(description = "사용자가 선택한 초기 난이도 그룹", example = "INTERMEDIATE")
  private LevelTestDifficultyGroup difficultyGroup;
}
