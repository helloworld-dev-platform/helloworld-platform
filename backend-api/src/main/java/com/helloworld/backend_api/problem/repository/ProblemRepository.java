package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long>, ProblemRepositoryQuery {

}
