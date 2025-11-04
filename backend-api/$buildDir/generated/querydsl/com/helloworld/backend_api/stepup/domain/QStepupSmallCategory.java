package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupSmallCategory is a Querydsl query type for StepupSmallCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupSmallCategory extends EntityPathBase<StepupSmallCategory> {

    private static final long serialVersionUID = -957362245L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupSmallCategory stepupSmallCategory = new QStepupSmallCategory("stepupSmallCategory");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStepupMediumCategory mediumCategoryId;

    public final ListPath<com.helloworld.backend_api.problem.domain.Problem, com.helloworld.backend_api.problem.domain.QProblem> problems = this.<com.helloworld.backend_api.problem.domain.Problem, com.helloworld.backend_api.problem.domain.QProblem>createList("problems", com.helloworld.backend_api.problem.domain.Problem.class, com.helloworld.backend_api.problem.domain.QProblem.class, PathInits.DIRECT2);

    public final NumberPath<Integer> SmallCategoryOrder = createNumber("SmallCategoryOrder", Integer.class);

    public final StringPath smallCategoryTitle = createString("smallCategoryTitle");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupSmallCategory(String variable) {
        this(StepupSmallCategory.class, forVariable(variable), INITS);
    }

    public QStepupSmallCategory(Path<? extends StepupSmallCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupSmallCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupSmallCategory(PathMetadata metadata, PathInits inits) {
        this(StepupSmallCategory.class, metadata, inits);
    }

    public QStepupSmallCategory(Class<? extends StepupSmallCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mediumCategoryId = inits.isInitialized("mediumCategoryId") ? new QStepupMediumCategory(forProperty("mediumCategoryId"), inits.get("mediumCategoryId")) : null;
    }

}

