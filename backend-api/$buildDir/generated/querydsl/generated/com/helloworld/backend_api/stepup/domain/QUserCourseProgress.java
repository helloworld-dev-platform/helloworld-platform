package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCourseProgress is a Querydsl query type for UserCourseProgress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCourseProgress extends EntityPathBase<UserCourseProgress> {

    private static final long serialVersionUID = 447050934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCourseProgress userCourseProgress = new QUserCourseProgress("userCourseProgress");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.helloworld.backend_api.problem.domain.QProblem problemId;

    public final EnumPath<StepupStatus> status = createEnum("status", StepupStatus.class);

    public final StringPath tempAnswer = createString("tempAnswer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.helloworld.backend_api.user.domain.QUser user;

    public QUserCourseProgress(String variable) {
        this(UserCourseProgress.class, forVariable(variable), INITS);
    }

    public QUserCourseProgress(Path<? extends UserCourseProgress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCourseProgress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCourseProgress(PathMetadata metadata, PathInits inits) {
        this(UserCourseProgress.class, metadata, inits);
    }

    public QUserCourseProgress(Class<? extends UserCourseProgress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problemId = inits.isInitialized("problemId") ? new com.helloworld.backend_api.problem.domain.QProblem(forProperty("problemId"), inits.get("problemId")) : null;
        this.user = inits.isInitialized("user") ? new com.helloworld.backend_api.user.domain.QUser(forProperty("user")) : null;
    }

}

