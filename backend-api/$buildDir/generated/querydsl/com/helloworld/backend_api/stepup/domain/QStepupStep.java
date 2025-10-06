package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupStep is a Querydsl query type for StepupStep
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupStep extends EntityPathBase<StepupStep> {

    private static final long serialVersionUID = 844223862L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupStep stepupStep = new QStepupStep("stepupStep");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.helloworld.backend_api.problem.domain.Problem, com.helloworld.backend_api.problem.domain.QProblem> problems = this.<com.helloworld.backend_api.problem.domain.Problem, com.helloworld.backend_api.problem.domain.QProblem>createList("problems", com.helloworld.backend_api.problem.domain.Problem.class, com.helloworld.backend_api.problem.domain.QProblem.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stepOrder = createNumber("stepOrder", Integer.class);

    public final StringPath stepTitle = createString("stepTitle");

    public final QStepupSection stepupSection;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupStep(String variable) {
        this(StepupStep.class, forVariable(variable), INITS);
    }

    public QStepupStep(Path<? extends StepupStep> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupStep(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupStep(PathMetadata metadata, PathInits inits) {
        this(StepupStep.class, metadata, inits);
    }

    public QStepupStep(Class<? extends StepupStep> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stepupSection = inits.isInitialized("stepupSection") ? new QStepupSection(forProperty("stepupSection"), inits.get("stepupSection")) : null;
    }

}

