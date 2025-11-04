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
  @Schema(description = "학습언어 ID", example = "1") // 필드에 대한 설명과 예시
  private Long languageId;

  @NotNull
  @Schema(description = "테스트 난이도 (필수)",
      allowableValues = {"EASY", "MEDIUM", "HARD"},
      example = "EASY")
  private Difficulty difficulty;

}
