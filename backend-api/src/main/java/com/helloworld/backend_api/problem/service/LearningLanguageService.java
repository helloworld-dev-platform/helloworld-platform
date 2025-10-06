package com.helloworld.backend_api.problem.service;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import com.helloworld.backend_api.problem.dto.LearningLanguageDto;
import com.helloworld.backend_api.problem.repository.LearningLanguageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LearningLanguageService {

  private final LearningLanguageRepository learningLanguageRepository;

  @Transactional(readOnly = true)
  public List<LearningLanguageDto> findAllLanguages() {
    List<LearningLanguage> languages = learningLanguageRepository.findAll();
    return languages.stream()
        .map(LearningLanguageDto::from)
        .collect(Collectors.toList());
  }

}
