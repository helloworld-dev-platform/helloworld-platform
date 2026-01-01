package com.helloworld.backend_api.problem.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Difficulty {
  LEVEL_1(1, "LEVEL_1", "1단계"),
  LEVEL_2(2, "LEVEL_2", "2단계"),
  LEVEL_3(3, "LEVEL_3", "3단계"),
  LEVEL_4(4, "LEVEL_4", "4단계"),
  LEVEL_5(5, "LEVEL_5", "5단계"),
  LEVEL_6(6, "LEVEL_6", "6단계"),
  LEVEL_7(7, "LEVEL_7", "7단계"),
  LEVEL_8(8, "LEVEL_8", "8단계"),
  LEVEL_9(9, "LEVEL_9", "9단계"),
  LEVEL_10(10, "LEVEL_10", "10단계");

  private final int level;         // 숫자 레벨 (정렬, 계산용)
  private final String code;       // DB 저장, API 식별용
  private final String description; // UI 표시용

  private static final Map<Integer, Difficulty> BY_LEVEL =
      Arrays.stream(values())
          .collect(Collectors.toMap(Difficulty::getLevel, Function.identity()));


  private static final Map<String, Difficulty> BY_CODE =
      Arrays.stream(values())
          .collect(Collectors.toMap(Difficulty::getCode, Function.identity()));

  /**
   * 숫자 레벨로 Difficulty Enum을 찾습니다.
   *
   * @param level 1~10 사이의 숫자
   * @return Difficulty Enum
   * @throws IllegalArgumentException 유효하지 않은 레벨일 경우
   */
  public static Difficulty ofLevel(int level) {
    Difficulty difficulty = BY_LEVEL.get(level);
    if (difficulty == null) {
      throw new IllegalArgumentException("유효하지 않은 레벨입니다: " + level);
    }
    return difficulty;
  }

  /**
   * 코드로 Difficulty Enum을 찾습니다.
   *
   * @param code "LEVEL_1" 등의 문자열
   * @return Difficulty Enum
   * @throws IllegalArgumentException 유효하지 않은 코드일 경우
   */
  public static Difficulty ofCode(String code) {
    Difficulty difficulty = BY_CODE.get(code);
    if (difficulty == null) {
      throw new IllegalArgumentException("유효하지 않은 코드입니다: " + code);
    }
    return difficulty;
  }

  /**
   * 정답 여부에 따라 다음 레벨을 계산하여 반환 Service 계층의 복잡도를 낮추기 위해 도메인 내부에 로직 포함
   *
   * @param isCorrect 정답 여부
   * @return 조정된 Difficulty Enum
   */
  public Difficulty adjustLevel(boolean isCorrect) {
    int nextLevel = isCorrect ? this.level + 1 : this.level - 1;

    // 경계값 처리 (1 미만으로 내려가거나 10 초과로 올라가지 않도록 방어)
    if (nextLevel > 10) {
      return LEVEL_10;
    }
    if (nextLevel < 1) {
      return LEVEL_1;
    }

    // 안전하게 범위가 검증된 nextLevel로 Enum 조회
    return ofLevel(nextLevel);
  }
}
