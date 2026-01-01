package com.helloworld.backend_api.pretest.dto;

import com.helloworld.backend_api.problem.domain.Difficulty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "사전레벨테스트 문제목록 조회요청 DTO")
public class PreTestProblemRequest {

  @NotNull
  @Schema(description = "학습언어 ID", example = "1")
  private Long languageId;

  @NotNull
  @Schema(description = "테스트 난이도 (필수)",
      allowableValues = {"LEVEL_1", "LEVEL_2", "LEVEL_3", "LEVEL_4", "LEVEL_5", "LEVEL_6",
          "LEVEL_7", "LEVEL_8", "LEVEL_9", "LEVEL_10"},
      example = "LEVEL_1")
  private Difficulty difficulty;

}
