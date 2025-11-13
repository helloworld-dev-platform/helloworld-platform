package com.helloworld.backend_api.pretest.controller;

import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.pretest.dto.PreTestProblemRequest;
import com.helloworld.backend_api.pretest.dto.PreTestProblemResponse;
import com.helloworld.backend_api.pretest.service.PreTestProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "PreTest", description = "사전레벨테스트 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/pretest")
public class PreTestController {

  private final PreTestProblemService preTestProblemService;

  @Operation(
      summary = "사전 레벨 테스트 문제 목록 조회",
      description = "사용자가 선택한 언어와 난이도 조건에 맞는 문제 목록을 조회합니다. 이 API는 테스트 시작 전에 호출됩니다."
  )
  @ApiErrorCodeExamples({
      ErrorCode.PROBLEM_NOT_FOUND,
      ErrorCode.LANGUAGE_NOT_FOUND,
      ErrorCode.INTERNAL_SERVER_ERROR
  })
  @PostMapping("/problems") // GET 대신 POST를 사용하여 요청 본문으로 조건을 받습니다.
  public DataResponseDto<List<PreTestProblemResponse>> getPreTestProblems(
      @RequestBody @Valid PreTestProblemRequest request
  ) {
    // 2. 서비스 위임 및 DTO 획득
    List<PreTestProblemResponse> problems = preTestProblemService.getPreTestProblemList(request);

    // 3. 응답 반환
    return DataResponseDto.from(problems);
  }
}
