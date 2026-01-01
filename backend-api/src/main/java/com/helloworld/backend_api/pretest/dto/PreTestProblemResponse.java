package com.helloworld.backend_api.pretest.dto;

import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.Problem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "사전 레벨 테스트 문제 목록 응답 DTO")
public class PreTestProblemResponse {

  @Schema(description = "문제 고유 ID", example = "18")
  private Long problemId; // 'id' 대신 'problemId'로 명확하게 네이밍

  @Schema(description = "학습 언어 ID", example = "1")
  private Long languageId;

  @Schema(description = "문제 내용 (지문)", example = "데이터를 저장하기 위해 이름을 붙인 메모리 공간은?")
  private String content;

  @Schema(description = "문제 난이도", example = "EASY", allowableValues = {"EASY", "MEDIUM", "HARD"})
  private Difficulty difficulty;

  @Schema(description = "문제 유형", example = "SUBJECTIVE", allowableValues = {"MULTIPLE_CHOICE",
      "SUBJECTIVE", "FILL_IN_THE_BLANK"})
  private String problemType; // ProblemType ENUM의 String 값

  @Schema(description = "객관식 선택지 목록 (주관식/빈칸일 경우 null 또는 빈 리스트)")
  private List<PreTestChoiceResponse> choices;


  /**
   * 책임 분리: Problem 엔티티를 DTO로 변환하는 정적 팩토리 메소드
   */
  public static PreTestProblemResponse from(Problem problem, List<Choice> choiceEntities) {
    // Choice Entity 리스트 -> DTO 리스트 변환
    List<PreTestChoiceResponse> choiceDtos = (choiceEntities == null || choiceEntities.isEmpty())
        ? Collections.emptyList()
        : choiceEntities.stream().map(PreTestChoiceResponse::from).collect(Collectors.toList());

    return PreTestProblemResponse.builder()
        .problemId(problem.getId())
        .languageId(problem.getLanguageId().getId())
        .content(problem.getContent())
        .difficulty(Difficulty.ofCode(problem.getDifficulty().toString())) // String -> Enum 변환
        .problemType(problem.getProblemType().toString()) // String 유지
        .choices(choiceDtos)
        .build();
  }

  @Getter
  @Builder
  @AllArgsConstructor
  @Schema(description = "객관식 선택지 정보")
  static class PreTestChoiceResponse {

    @Schema(description = "선택지 ID (답안 제출 시 사용)", example = "101")
    private Long choiceId;

    @Schema(description = "선택지 내용", example = "[]")
    private String content;

    public static PreTestChoiceResponse from(Choice choice) {
      return PreTestChoiceResponse.builder()
          .choiceId(choice.getId())
          .content(choice.getContent())
          .build();
    }

  }
}
