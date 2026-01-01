package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Solution;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

  Optional<Solution> findByProblemId(Long problemId);
}
