package com.helloworld.backend_api.common.config;

import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.common.response.ErrorResponseDto;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExample;
import com.helloworld.backend_api.common.swagger.ApiErrorCodeExamples;
import com.helloworld.backend_api.common.swagger.ExampleHolder;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

@Component
@RequiredArgsConstructor
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("HELLOWORLD API")
                .version("1.0.0")
        )
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new io.swagger.v3.oas.models.Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .name("Authorization")
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .description("JWT Bearer 인증 헤더 입력"))
        );
  }


  @Bean
  public OperationCustomizer customize() {
    return (Operation operation, HandlerMethod handlerMethod) -> {
      ApiErrorCodeExamples apiErrorCodeExamples = handlerMethod.getMethodAnnotation(
          ApiErrorCodeExamples.class);

      // @ApiErrorCodeExamples 어노테이션이 붙어있다면
      if (apiErrorCodeExamples != null) {
        generateErrorCodeResponseExample(operation, apiErrorCodeExamples.value());
      } else {
        ApiErrorCodeExample apiErrorCodeExample = handlerMethod.getMethodAnnotation(
            ApiErrorCodeExample.class);

        // @ApiErrorCodeExamples 어노테이션이 붙어있지 않고
        // @ApiErrorCodeExample 어노테이션이 붙어있다면
        if (apiErrorCodeExample != null) {
          generateErrorCodeResponseExample(operation, apiErrorCodeExample.value());
        }
      }

      return operation;
    };
  }


  // 여러 개의 에러 응답값 추가
  private void generateErrorCodeResponseExample(Operation operation, ErrorCode[] errorCodes) {
    ApiResponses responses = operation.getResponses();

    // ExampleHolder(에러 응답값) 객체를 만들고 에러 코드별로 그룹화
    Map<Integer, List<ExampleHolder>> statusWithExampleHolders = Arrays.stream(errorCodes)
        .map(
            errorCode -> ExampleHolder.builder()
                .holder(getSwaggerExample(errorCode))
                .code(errorCode.getHttpStatus().value())
                .name(errorCode.name())
                .build()
        )
        .collect(Collectors.groupingBy(ExampleHolder::getCode));

    // ExampleHolders를 ApiResponses에 추가
    addExamplesToResponses(responses, statusWithExampleHolders);
  }

  // 단일 에러 응답값 예시 추가
  private void generateErrorCodeResponseExample(Operation operation, ErrorCode errorCode) {
    ApiResponses responses = operation.getResponses();

    // ExampleHolder 객체 생성 및 ApiResponses에 추가
    ExampleHolder exampleHolder = ExampleHolder.builder()
        .holder(getSwaggerExample(errorCode))
        .name(errorCode.name())
        .code(errorCode.getHttpStatus().value())
        .build();
    addExamplesToResponses(responses, exampleHolder);
  }

  // ErrorResponseDto 형태의 예시 객체 생성
  private Example getSwaggerExample(ErrorCode errorCode) {
    ErrorResponseDto errorResponseDto = ErrorResponseDto.from(errorCode);
    Example example = new Example();
    example.setValue(errorResponseDto);

    return example;
  }

  // exampleHolder를 ApiResponses에 추가
  private void addExamplesToResponses(ApiResponses responses,
      Map<Integer, List<ExampleHolder>> statusWithExampleHolders) {
    statusWithExampleHolders.forEach(
        (status, v) -> {
          Content content = new Content();
          MediaType mediaType = new MediaType();
          ApiResponse apiResponse = new ApiResponse();

          v.forEach(
              exampleHolder -> mediaType.addExamples(
                  exampleHolder.getName(),
                  exampleHolder.getHolder()
              )
          );
          content.addMediaType("application/json", mediaType);
          apiResponse.setContent(content);
          responses.addApiResponse(String.valueOf(status), apiResponse);
        }
    );
  }

  private void addExamplesToResponses(ApiResponses responses, ExampleHolder exampleHolder) {
    Content content = new Content();
    MediaType mediaType = new MediaType();
    ApiResponse apiResponse = new ApiResponse();

    mediaType.addExamples(exampleHolder.getName(), exampleHolder.getHolder());
    content.addMediaType("application/json", mediaType);
    apiResponse.content(content);
    responses.addApiResponse(String.valueOf(exampleHolder.getCode()), apiResponse);
  }
}
