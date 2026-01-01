package com.helloworld.backend_api.pretest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "사전 테스트 시작 응답")
public class PreTestStartResponse {

  @Schema(description = "생성된 테스트 세션 ID (종료 시 필요)", example = "105")
  private Long sessionId;

  @Schema(description = "첫 번째 문제 정보")
  private PreTestProblemResponse problem;
}
