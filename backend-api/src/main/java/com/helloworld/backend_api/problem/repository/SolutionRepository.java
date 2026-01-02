package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Solution;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

  @Query("SELECT s FROM Solution s WHERE s.problemId = :problemId")
  Optional<Solution> findByProblemId(@Param("problemId") Long problemId);
}
