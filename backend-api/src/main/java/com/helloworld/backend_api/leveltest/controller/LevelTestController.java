package com.helloworld.backend_api.leveltest.controller;

import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.leveltest.dto.LevelTestFinishRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestGradeRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestGradeResponse;
import com.helloworld.backend_api.leveltest.dto.LevelTestNextProblemRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestProblemResponse;
import com.helloworld.backend_api.leveltest.dto.LevelTestStartRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestStartResponse;
import com.helloworld.backend_api.leveltest.service.LevelTestProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "LevelTest", description = "사전레벨테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/leveltest")
public class LevelTestController {

  private final LevelTestProblemService levelTestProblemService;


  @Operation(
      summary = "사전 테스트 시작 (첫 번째 문제 조회)",
      description = """
          사용자가 선택한 언어와 난이도 그룹(초급/중급/고급)을 기반으로 **첫 번째 문제**를 출제함.
                      
          - **BEGINNER**: LEVEL_1 문제 출제
          - **INTERMEDIATE**: LEVEL_4 문제 출제
          - **ADVANCED**: LEVEL_8 문제 출제
          """
  )
  @ApiResponse(responseCode = "200", description = "요청 성공")
  @ApiErrorCodeExamples({
      ErrorCode.LANGUAGE_NOT_FOUND,  // 언어 ID 잘못됨
      ErrorCode.PROBLEM_NOT_FOUND,   // 문제 없음
      ErrorCode.INVALID_INPUT_VALUE, // 요청값(Enum 등) 검증 실패
      ErrorCode.INTERNAL_SERVER_ERROR
  })
  @PostMapping("/start")
  public DataResponseDto<LevelTestStartResponse> startLevelTest(
      @AuthenticationPrincipal Long userId,
      @RequestBody @Valid LevelTestStartRequest req) {
    if (userId == null) {
      userId = 9999L;
    }
    return DataResponseDto.from(levelTestProblemService.getStartProblem(userId, req));
  }


  @Operation(summary = "문제 채점", description = "답안을 제출하여 채점하고 이력을 저장함")
  @ApiResponse(responseCode = "200", description = "요청 성공")
  @ApiErrorCodeExamples({
      ErrorCode.PROBLEM_NOT_FOUND,        // 문제 ID 없음
      ErrorCode.CHOICE_NOT_FOUND,         // 객관식 선택지 ID 없음 (추가 필요)
      ErrorCode.SOLUTION_NOT_FOUND,       // 정답 데이터 누락 (추가 필요)
      ErrorCode.UNSUPPORTED_PROBLEM_TYPE, // 지원 안하는 유형 (추가 필요)
      ErrorCode.INVALID_INPUT_VALUE       // 필수값 누락 등
  })
  @PostMapping("/grade")
  public DataResponseDto<LevelTestGradeResponse> gradeProblem(
      @Parameter(description = "인증된 사용자 ID (Token에서 추출)", hidden = true)
      @AuthenticationPrincipal Long userId,
      @RequestBody @Valid LevelTestGradeRequest req) {

    if (userId == null) {
      userId = 9999L; // 테스트용 임시 처리
    }

    return DataResponseDto.from(levelTestProblemService.gradeProblem(userId, req));
  }

  @Operation(summary = "다음 문제 조회", description = "이전 결과에 따라 난이도를 조정하여 다음 문제를 가져옴")
  @ApiResponse(responseCode = "200", description = "요청 성공")
  @ApiErrorCodeExamples({
      ErrorCode.NO_MORE_PROBLEMS,     // 해당 난이도 문제 고갈 (추가 필요)
      ErrorCode.LANGUAGE_NOT_FOUND,   // 언어 오류
      ErrorCode.INVALID_INPUT_VALUE   // Enum 파싱 오류 등
  })
  @PostMapping("/next")
  public DataResponseDto<LevelTestProblemResponse> getNextProblem(
      @RequestBody @Valid LevelTestNextProblemRequest req) {
    return DataResponseDto.from(levelTestProblemService.getNextProblem(req));
  }

  @Operation(summary = "테스트 종료 및 결과 저장", description = "세션을 종료하고 최종 레벨 결과를 저장합니다.")
  @ApiResponse(responseCode = "200", description = "저장 성공")
  @PostMapping("/finish")
  public DataResponseDto<Void> finishLevelTest(
      @AuthenticationPrincipal Long userId,
      @RequestBody @Valid LevelTestFinishRequest req) {
    if (userId == null) {
      userId = 9999L;
    }

    levelTestProblemService.finishLevelTest(userId, req);
    return DataResponseDto.from(null); // 데이터 없이 성공 응답
  }
}
