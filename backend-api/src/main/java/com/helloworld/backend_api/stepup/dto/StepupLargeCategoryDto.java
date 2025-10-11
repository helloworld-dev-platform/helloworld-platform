package com.helloworld.backend_api.stepup.dto;

import com.helloworld.backend_api.stepup.domain.StepupLargeCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "스텝업 대분류 정보 응답 DTO")
public class StepupLargeCategoryDto {

  @Schema(description = "대분류 고유 ID", example = "1")
  private final Long id;

  @Schema(description = "대분류 이름", example = "파이썬 기초")
  private final String largeCategoryName;

  @Builder
  public StepupLargeCategoryDto(Long id, String largeCategoryName) {
    this.id = id;
    this.largeCategoryName = largeCategoryName;
  }

  public static StepupLargeCategoryDto from(StepupLargeCategory course) {
    return StepupLargeCategoryDto.builder()
        .id(course.getId())
        .largeCategoryName(course.getLargeCategoryName())
        .build();
  }
}
