package com.helloworld.backend_api.problem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblem is a Querydsl query type for Problem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblem extends EntityPathBase<Problem> {

    private static final long serialVersionUID = -886661532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblem problem = new QProblem("problem");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<Difficulty> difficulty = createEnum("difficulty", Difficulty.class);

    public final EnumPath<DomainType> domainType = createEnum("domainType", DomainType.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLearningLanguage languageId;

    public final EnumPath<ProblemType> problemType = createEnum("problemType", ProblemType.class);

    public final com.helloworld.backend_api.stepup.domain.QStepupSmallCategory smallCategoryId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QProblem(String variable) {
        this(Problem.class, forVariable(variable), INITS);
    }

    public QProblem(Path<? extends Problem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblem(PathMetadata metadata, PathInits inits) {
        this(Problem.class, metadata, inits);
    }

    public QProblem(Class<? extends Problem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.languageId = inits.isInitialized("languageId") ? new QLearningLanguage(forProperty("languageId")) : null;
        this.smallCategoryId = inits.isInitialized("smallCategoryId") ? new com.helloworld.backend_api.stepup.domain.QStepupSmallCategory(forProperty("smallCategoryId"), inits.get("smallCategoryId")) : null;
    }

}

