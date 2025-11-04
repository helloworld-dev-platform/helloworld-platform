package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserStepupSubmissionHistory is a Querydsl query type for UserStepupSubmissionHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserStepupSubmissionHistory extends EntityPathBase<UserStepupSubmissionHistory> {

    private static final long serialVersionUID = 299170419L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserStepupSubmissionHistory userStepupSubmissionHistory = new QUserStepupSubmissionHistory("userStepupSubmissionHistory");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isCorrect = createBoolean("isCorrect");

    public final com.helloworld.backend_api.problem.domain.QProblem problem;

    public final com.helloworld.backend_api.problem.domain.QChoice selectedChoice;

    public final StringPath textAnswer = createString("textAnswer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.helloworld.backend_api.user.domain.QUser user;

    public QUserStepupSubmissionHistory(String variable) {
        this(UserStepupSubmissionHistory.class, forVariable(variable), INITS);
    }

    public QUserStepupSubmissionHistory(Path<? extends UserStepupSubmissionHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserStepupSubmissionHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserStepupSubmissionHistory(PathMetadata metadata, PathInits inits) {
        this(UserStepupSubmissionHistory.class, metadata, inits);
    }

    public QUserStepupSubmissionHistory(Class<? extends UserStepupSubmissionHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problem = inits.isInitialized("problem") ? new com.helloworld.backend_api.problem.domain.QProblem(forProperty("problem"), inits.get("problem")) : null;
        this.selectedChoice = inits.isInitialized("selectedChoice") ? new com.helloworld.backend_api.problem.domain.QChoice(forProperty("selectedChoice"), inits.get("selectedChoice")) : null;
        this.user = inits.isInitialized("user") ? new com.helloworld.backend_api.user.domain.QUser(forProperty("user")) : null;
    }

}

