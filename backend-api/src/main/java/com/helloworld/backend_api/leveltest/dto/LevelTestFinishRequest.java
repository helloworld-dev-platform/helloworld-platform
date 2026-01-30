package com.helloworld.backend_api.leveltest.dto;

import com.helloworld.backend_api.problem.domain.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사전 테스트 종료 및 결과 저장 요청")
public class LevelTestFinishRequest {

  @NotNull
  @Schema(description = "종료할 세션 ID", example = "105")
  private Long sessionId;

  @NotNull
  @Schema(description = "학습 언어 ID", example = "1")
  private Long languageId;

  @NotNull
  @Schema(description = "최종 도달한 난이도 (결과)", example = "LEVEL_6")
  private Difficulty finalDifficulty;
}
