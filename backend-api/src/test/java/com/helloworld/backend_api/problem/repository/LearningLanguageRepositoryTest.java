package com.helloworld.backend_api.problem.repository;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class LearningLanguageRepositoryTest {

  @Autowired
  private LearningLanguageRepository learningLanguageRepository;

  @Test
  @DisplayName("저장된 학습 언어를 성공적으로 조회한다.")
  public void findAllLearningLanguages() throws Exception {
    //given
    LearningLanguage python = LearningLanguage.builder()
        .languageName("Python")
        .build();
    LearningLanguage java = LearningLanguage.builder()
        .languageName("Java")
        .build();
    learningLanguageRepository.saveAll(List.of(python, java));

    //when
    List<LearningLanguage> languages = learningLanguageRepository.findAll();

    //then
    assertThat(languages).hasSize(2);
    assertThat(languages).extracting("languageName")
        .containsExactlyInAnyOrder("Python", "Java");
  }

}
