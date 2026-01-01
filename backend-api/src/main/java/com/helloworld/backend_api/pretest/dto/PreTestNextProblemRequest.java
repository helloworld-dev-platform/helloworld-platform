package com.helloworld.backend_api.pretest.dto;

import com.helloworld.backend_api.problem.domain.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "다음 문제 조회 요청 DTO")
public class PreTestNextProblemRequest {

  @NotNull
  @Schema(description = "학습 언어 ID", example = "1")
  private Long languageId;

  @NotNull
  @Schema(description = "직전 문제의 난이도 (이 값을 기준으로 레벨 조정)", example = "LEVEL_5")
  private Difficulty currentDifficulty;

  @Schema(description = "직전 문제 정답 여부 (true면 레벨업, false면 레벨다운)", example = "true")
  private boolean isLastCorrect;

  @Schema(description = "지금까지 푼 문제 ID 목록 (중복 출제 방지용)", example = "[10, 15, 23]")
  private List<Long> solvedProblemIds;
}
