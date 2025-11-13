package com.helloworld.backend_api.pretest.service;

import com.helloworld.backend_api.common.exception.CustomException;
import com.helloworld.backend_api.common.exception.ErrorCode;
import com.helloworld.backend_api.pretest.dto.PreTestProblemRequest;
import com.helloworld.backend_api.pretest.dto.PreTestProblemResponse;
import com.helloworld.backend_api.problem.domain.Problem;
import com.helloworld.backend_api.problem.repository.LearningLanguageRepository;
import com.helloworld.backend_api.problem.repository.ProblemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PreTestProblemService {

  private final ProblemRepository problemRepository;
  private final LearningLanguageRepository learningLanguageRepository;

  /**
   * 학습언어, 난이도 선택에 따른 사전레벨테스트 문제조회
   *
   * @param req
   * @return
   */
  public List<PreTestProblemResponse> getPreTestProblemList(PreTestProblemRequest req) {
    //1. 학습언어 ID 존재유무 확인 및 예외 처리
    validateLanguageExistence(req.getLanguageId());

    //2. 언어,난이도선택에 따른 문제 조회
    List<Problem> problems = problemRepository.findPreTestProblems(
        req.getLanguageId(),
        req.getDifficulty()
    );

    if (problems.isEmpty()) {
      throw new CustomException(ErrorCode.PROBLEM_NOT_FOUND);
    }

    return PreTestProblemResponse.fromList(problems);
  }

  /**
   * 요청값 유효성 검증 학습언어 ID 존재유무 판별
   *
   * @param languageId
   */
  private void validateLanguageExistence(Long languageId) {
    if (!learningLanguageRepository.existsById(languageId)) {
      throw new CustomException(ErrorCode.LANGUAGE_NOT_FOUND);
    }
  }


}