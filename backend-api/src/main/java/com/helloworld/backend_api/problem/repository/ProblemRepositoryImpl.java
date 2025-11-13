package com.helloworld.backend_api.problem.repository;

import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.DomainType;
import com.helloworld.backend_api.problem.domain.Problem;
import com.helloworld.backend_api.problem.domain.QProblem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProblemRepositoryImpl implements ProblemRepositoryQuery {

  private final JPAQueryFactory queryFactory;
  private static final String DOMAIN_TYPE_PRE_TEST = "PRE_TEST";


  @Override
  public List<Problem> findPreTestProblems(@NotNull Long languageId, Difficulty difficulty) {

    QProblem problem = QProblem.problem;

    return queryFactory
        .selectFrom(problem)
        .where(
            problem.languageId.id.eq(languageId),
            problem.difficulty.eq(difficulty),
            problem.domainType.eq(DomainType.PRE_TEST)
        )
        .fetch();
  }
}
