package com.helloworld.backend_api.leveltest.component;

import com.helloworld.backend_api.problem.domain.Choice;
import com.helloworld.backend_api.problem.domain.ProblemExplanation;
import com.helloworld.backend_api.problem.repository.ChoiceRepository;
import com.helloworld.backend_api.problem.repository.ProblemExplanationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelTestCachedReader {

  private final ChoiceRepository choiceRepository;
  private final ProblemExplanationRepository explanationRepository;

  // 객관식 보기 캐싱 (key: problemId)
  @Cacheable(value = "problemChoices", key = "#problemId", unless = "#result == null")
  public List<Choice> getChoices(Long problemId) {
    return choiceRepository.findAllByProblemId(problemId);
  }

  // 해설 캐싱 (key: problemId)
  @Cacheable(value = "problemExplanation", key = "#problemId", unless = "#result == null")
  public String getExplanation(Long problemId) {
    return explanationRepository.findByProblemId(problemId)
        .map(ProblemExplanation::getContent)
        .orElse(null);
  }
}
