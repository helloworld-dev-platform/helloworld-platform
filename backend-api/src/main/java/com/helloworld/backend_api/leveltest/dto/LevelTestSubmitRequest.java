package com.helloworld.backend_api.leveltest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사전 테스트 문제 답안 제출 요청 DTO")
public class LevelTestSubmitRequest {

  @NotNull(message = "문제 ID는 필수입니다.")
  @Schema(description = "제출 문제의 ID", example = "18")
  private Long problemId;

  @Schema(description = "객관식 선택지의 ID (객관식 문제일 경우)", example = "101", nullable = true)
  private Long choiceId;

  @NotNull(message = "답안 목록은 필수입니다.")
  @Schema(description = "주관식/빈칸 채우기 답안 목록 (빈칸 채우기는 여러 개, 주관식은 1개)"
      + "문제빈칸순서에 맞춰서 배열인덱스 순서 맞춰주어야함", nullable = true)
  private List<String> submitAnswerList;
}
