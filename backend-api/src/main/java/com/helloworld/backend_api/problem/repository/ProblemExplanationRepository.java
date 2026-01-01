package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.ProblemExplanation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemExplanationRepository extends JpaRepository<ProblemExplanation, Long> {

  Optional<ProblemExplanation> findByProblemId(Long problemId);
}
