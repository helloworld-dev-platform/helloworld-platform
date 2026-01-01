package com.helloworld.backend_api.pretest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "채점 결과 응답 DTO")
public class PreTestGradeResponse {

  @Schema(description = "정답 여부", example = "true")
  private boolean isCorrect;

  @Schema(description = "채점된 문제 ID", example = "10")
  private Long problemId;

  @Schema(description = "문제 해설", example = "객체지향 프로그래밍에서 상속은...")
  private String explanation;
}
