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
@Schema(description = "사전레벨테스트 문제 채점 요청 DTO")
public class LevelTestGradeRequest {

  @NotNull
  @Schema(description = "문제 ID", example = "1")
  private Long problemId;

  @NotNull
  @Schema(description = "문제유형",
      allowableValues = {"MULTIPLE_CHOICE", "SUBJECTIVE", "FILL_IN_THE_BLANK"},
      example = "MULTIPLE_CHOICE")
  private String problemType;

  @Schema(description = "사용자가 입력한 정답 (주관식/빈칸은 문자열, 객관식은 선택지 ID 혹은 내용)")
  private String userAnswer;

  @Schema(description = "빈칸 채우기용 정답 리스트 (순서 중요)")
  private List<String> userAnswers;
}
