package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.BlankSolution;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlankSolutionRepository extends JpaRepository<BlankSolution, Long> {

  List<BlankSolution> findAllByProblemIdOrderByBlankOrderAsc(Long problemId);
}
