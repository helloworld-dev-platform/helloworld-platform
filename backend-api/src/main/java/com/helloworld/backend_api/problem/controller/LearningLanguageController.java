package com.helloworld.backend_api.problem.controller;

import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.DataResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.problem.dto.LearningLanguageDto;
import com.helloworld.backend_api.problem.service.LearningLanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "학습언어", description = "학습언어 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/dev/language")
public class LearningLanguageController {

  private final LearningLanguageService learningLanguageService;

  @Operation(summary = "학습 언어 전체 목록 조회", description = "플랫폼에서 학습 가능한 프로그래밍 언어의 전체 목록을 조회합니다.")
  // API 기능 요약 및 설명
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공"), // 성공 응답에 대한 설명
  })
  @ApiErrorCodeExamples({
      ErrorCode.INTERNAL_SERVER_ERROR
  })
  @GetMapping()
  public DataResponseDto<List<LearningLanguageDto>> getLanguages() {
    List<LearningLanguageDto> languages = learningLanguageService.findAllLanguages();
    log.info("languages:{}", languages);
    return DataResponseDto.from(languages);
  }

}
