package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Choice;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

  List<Choice> findAllByProblemId(Long problemId);

  Optional<Choice> findByProblemIdAndContent(Long problemId, String content);
}
