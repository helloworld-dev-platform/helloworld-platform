package com.helloworld.backend_api.problem.repository;

import static com.helloworld.backend_api.problem.domain.QProblem.problem;

import com.helloworld.backend_api.problem.domain.Difficulty;
import com.helloworld.backend_api.problem.domain.DomainType;
import com.helloworld.backend_api.problem.domain.Problem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProblemQueryRepository {

  private final JPAQueryFactory queryFactory;

  /**
   * 랜덤 문제 조회 (PostgreSQL random() 함수 사용) 제외할 ID가 있으면 필터링
   */
  public Optional<Problem> findRandomProblem(Long languageId, String difficulty,
      List<Long> excludedIds) {
    return Optional.ofNullable(
        queryFactory
            .selectFrom(problem)
            .where(
                problem.languageId.id.eq(languageId),
                problem.difficulty.eq(Difficulty.valueOf(difficulty)),
                problem.domainType.eq(DomainType.valueOf("LEVEL_TEST")),
                excludedIdsNotIn(excludedIds) // 동적 쿼리
            )
            // PostgreSQL: ORDER BY random()
            .orderBy(Expressions.numberTemplate(Double.class, "function('random')").asc())
            .fetchFirst() // LIMIT 1
    );
  }

  private BooleanExpression excludedIdsNotIn(List<Long> excludedIds) {
    return (excludedIds != null && !excludedIds.isEmpty())
        ? problem.id.notIn(excludedIds)
        : null;
  }

}
