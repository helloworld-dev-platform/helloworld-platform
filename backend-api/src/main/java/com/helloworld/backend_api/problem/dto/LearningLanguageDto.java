package com.helloworld.backend_api.problem.dto;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Schema(description = "학습 언어 정보 응답 DTO")
public class LearningLanguageDto {

  @Schema(description = "언어 고유 ID", example = "1") // 필드에 대한 설명과 예시
  private final Long id;

  @Schema(description = "언어 이름", example = "Python")
  private final String languageName;

  @Builder
  public LearningLanguageDto(Long id, String languageName) {
    this.id = id;
    this.languageName = languageName;
  }

  public static LearningLanguageDto from(LearningLanguage entity) {
    return LearningLanguageDto.builder()
        .id(entity.getId())
        .languageName(entity.getLanguageName())
        .build();
  }
}
