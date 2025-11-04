package com.helloworld.backend_api.pretest.dto;

import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.LearningLanguage;
import com.helloworld.backend_api.problem.domain.Problem;
import io.swagger.v3.oas.annotations.media.Schema;
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
  private LearningLanguage languageId;

  @Schema(description = "문제 내용 (지문)", example = "데이터를 저장하기 위해 이름을 붙인 메모리 공간은?")
  private String content;

  @Schema(description = "문제 난이도", example = "EASY", allowableValues = {"EASY", "MEDIUM", "HARD"})
  private Difficulty difficulty;

  @Schema(description = "문제 유형", example = "SUBJECTIVE", allowableValues = {"MULTIPLE_CHOICE",
      "SUBJECTIVE", "FILL_IN_THE_BLANK"})
  private String problemType; // ProblemType ENUM의 String 값

  @Schema(description = "문제 도메인 유형 (사전 테스트)", example = "PRE_TEST")
  private String domainType;

  /**
   * 책임 분리: Problem 엔티티를 DTO로 변환하는 정적 팩토리 메소드
   */
  public static PreTestProblemResponse from(Problem problem) {
    return PreTestProblemResponse.builder()
        .problemId(problem.getId())
        .languageId(problem.getLanguageId())
        .content(problem.getContent())
        .difficulty(problem.getDifficulty())
        .problemType(problem.getProblemType().name()) // Enum을 문자열로 변환
        .domainType(problem.getDomainType().name())   // Enum을 문자열로 변환
        .build();
  }

  /**
   * 목록 변환을 위한 헬퍼 메소드 (Service에서 사용)
   */
  public static List<PreTestProblemResponse> fromList(List<Problem> problems) {
    return problems.stream()
        .map(PreTestProblemResponse::from)
        .collect(Collectors.toList());
  }
}
