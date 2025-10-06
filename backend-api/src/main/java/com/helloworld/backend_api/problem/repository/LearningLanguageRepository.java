package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.LearningLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningLanguageRepository extends JpaRepository<LearningLanguage, Long> {

}
