package com.helloworld.backend_api.leveltest.service;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.leveltest.domain.LevelTestLevel;
import com.helloworld.backend_api.leveltest.domain.UserLevelTestResult;
import com.helloworld.backend_api.leveltest.domain.UserLevelTestSession;
import com.helloworld.backend_api.leveltest.dto.LevelTestFinishRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestGradeRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestGradeResponse;
import com.helloworld.backend_api.leveltest.dto.LevelTestNextProblemRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestProblemResponse;
import com.helloworld.backend_api.leveltest.dto.LevelTestStartRequest;
import com.helloworld.backend_api.leveltest.dto.LevelTestStartResponse;
import com.helloworld.backend_api.leveltest.repository.LevelTestLevelRepository;
import com.helloworld.backend_api.leveltest.repository.UserLevelTestSessionRepository;
import com.helloworld.backend_api.leveltest.strategy.GraderFactory;
import com.helloworld.backend_api.leveltest.strategy.GradingResult;
import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.LearningLanguage;
import com.helloworld.backend_api.problem.domain.Problem;
import com.helloworld.backend_api.problem.repository.ChoiceRepository;
import com.helloworld.backend_api.problem.repository.LearningLanguageRepository;
import com.helloworld.backend_api.problem.repository.ProblemQueryRepository;
import com.helloworld.backend_api.user.domain.User;
import com.helloworld.backend_api.user.repository.UserLevelTestResultRepository;
import com.helloworld.backend_api.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LevelTestProblemService {

  private final ProblemQueryRepository problemQueryRepository;
  private final UserLevelTestSessionRepository sessionRepository;
  private final UserLevelTestResultRepository resultRepository;
  private final LevelTestLevelRepository levelRepository;
  private final ChoiceRepository choiceRepository;
  private final GraderFactory graderFactory;
  private final com.helloworld.backend_api.leveltest.component.LevelTestCachedReader levelTestCachedReader;
  private final UserRepository userRepository;
  private final LearningLanguageRepository learningLanguageRepository;


  /**
   * 테스트 시작 (세션 생성 + 첫 문제)
   */
  @Transactional // 세션 저장을 위해 트랜잭션 필요
  public LevelTestStartResponse getStartProblem(Long userId, LevelTestStartRequest req) {
    Difficulty startDifficulty = req.getDifficultyGroup().getStartDifficulty();

    // [세션 생성 로직]
    // 1. 초기 레벨 ID 조회 (예: "LV1") - DB 데이터에 맞게 매핑 필요
    // 편의상 Difficulty Enum name("LEVEL_1")을 "LV1" 등으로 변환한다고 가정
    String levelName = convertToLevelName(startDifficulty);
    LevelTestLevel levelEntity = levelRepository.findByLevelTestLevelName(levelName)
        .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT_VALUE));

    User userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    LearningLanguage languageEntity = learningLanguageRepository.findById(req.getLanguageId())
        .orElseThrow(() -> new CustomException(ErrorCode.LANGUAGE_NOT_FOUND));

    // 2. 세션 Insert
    UserLevelTestSession session = UserLevelTestSession.builder()
        .user(userEntity)
        .languageId(languageEntity)
        .levelId(levelEntity)
        .build();
    sessionRepository.save(session);

    // 3. 문제 조회
    Problem problem = problemQueryRepository.findRandomProblem(
        req.getLanguageId(), startDifficulty.getCode(), null
    ).orElseThrow(() -> new CustomException(ErrorCode.PROBLEM_NOT_FOUND));

    // 4. 응답 (SessionId 포함)
    return LevelTestStartResponse.builder()
        .sessionId(session.getId())
        .problem(convertToDto(problem))
        .build();
  }


  /**
   * 채점 (이력 저장 안 함)
   */
  public LevelTestGradeResponse gradeProblem(Long userId, LevelTestGradeRequest req) {
    // 채점만 수행 (메모리 연산)
    GradingResult result = graderFactory.getGrader(req.getProblemType())
        .grade(req.getProblemId(), req);

    // **이력 저장 로직 삭제됨**

    String explanation = levelTestCachedReader.getExplanation(req.getProblemId());

    return LevelTestGradeResponse.builder()
        .problemId(req.getProblemId())
        .isCorrect(result.isCorrect())
        .explanation(explanation)
        .build();
  }


  /**
   * 다음 문제
   */
  public LevelTestProblemResponse getNextProblem(LevelTestNextProblemRequest req) {
    Difficulty nextDifficulty = req.getCurrentDifficulty().adjustLevel(req.isLastCorrect());

    Problem problem = problemQueryRepository.findRandomProblem(
        req.getLanguageId(), nextDifficulty.getCode(), req.getSolvedProblemIds()
    ).orElseThrow(() -> new CustomException(ErrorCode.NO_MORE_PROBLEMS));

    return convertToDto(problem);
  }


  /**
   * 테스트 종료 및 결과 저장
   */
  @Transactional
  public void finishLevelTest(Long userId, LevelTestFinishRequest req) {
    // 1. 세션 조회 및 종료 처리
    UserLevelTestSession session = sessionRepository.findById(req.getSessionId())
        .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT_VALUE)); // 세션 없음

    // 2. 최종 레벨 ID 조회
    String finalLevelName = convertToLevelName(req.getFinalDifficulty());
    LevelTestLevel finalLevel = levelRepository.findByLevelTestLevelName(finalLevelName)
        .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT_VALUE));

    // 3. 결과 테이블 저장
    UserLevelTestResult result = UserLevelTestResult.builder()
        .userId(userId)
        .learningLanguageId(req.getLanguageId())
        .levelTestLevelId(finalLevel.getId())
        .build();
    resultRepository.save(result);

    // 필요하다면 User 테이블의 Total Point 업데이트 등 보상 로직 추가
  }

  private String convertToLevelName(Difficulty difficulty) {
    // 예: LEVEL_1 -> LV1, LEVEL_10 -> LV10
    return difficulty.name().replace("LEVEL_", "LV");
  }

  private LevelTestProblemResponse convertToDto(Problem problem) {
    List<Choice> choices = new ArrayList<>();

    // 객관식인 경우에만 보기 조회
    if ("MULTIPLE_CHOICE".equals(problem.getProblemType())) {
      choices = choiceRepository.findAllByProblemId(problem.getId());
      Collections.shuffle(choices); // 보기 순서 섞기 (옵션)
    }

    return LevelTestProblemResponse.from(problem, choices);
  }

//  /**
//   * 학습언어, 난이도 선택에 따른 사전레벨테스트 문제조회
//   *
//   * @param req
//   * @return
//   */
//  public List<LevelTestProblemResponse> getLevelTestProblemList(LevelTestProblemRequest req) {
//    //1. 학습언어 ID 존재유무 확인 및 예외 처리
//    validateLanguageExistence(req.getLanguageId());
//
//    //2. 언어,난이도선택에 따른 문제 조회
//    List<Problem> problems = problemRepository.findLevelTestProblems(
//        req.getLanguageId(),
//        req.getDifficulty()
//    );
//
//    if (problems.isEmpty()) {
//      throw new CustomException(ErrorCode.PROBLEM_NOT_FOUND);
//    }
//
//    return LevelTestProblemResponse.fromList(problems);
//  }

//  /**
//   * 요청값 유효성 검증 학습언어 ID 존재유무 판별
//   *
//   * @param languageId
//   */
//  private void validateLanguageExistence(Long languageId) {
//    if (!learningLanguageRepository.existsById(languageId)) {
//      throw new CustomException(ErrorCode.LANGUAGE_NOT_FOUND);
//    }
//  }


}