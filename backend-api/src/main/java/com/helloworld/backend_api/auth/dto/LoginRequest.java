package com.helloworld.backend_api.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "일반 로그인 요청 DTO")
public class LoginRequest {

  @NotBlank(message = "이메일은 필수 입력 항목입니다.")
  @Email(message = "올바른 이메일 형식이 아닙니다.")
  @Schema(description = "사용자 이메일 (로그인 ID)", example = "test@gmail.com")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
  @Schema(description = "비밀번호")
  private String password;
}
