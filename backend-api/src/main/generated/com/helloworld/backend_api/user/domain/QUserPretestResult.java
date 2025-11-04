package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPretestResult is a Querydsl query type for UserPretestResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPretestResult extends EntityPathBase<UserPretestResult> {

    private static final long serialVersionUID = -1740017088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPretestResult userPretestResult = new QUserPretestResult("userPretestResult");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.helloworld.backend_api.problem.domain.QLearningLanguage learningLanguage;

    public final com.helloworld.backend_api.pretest.domain.QPreTestLevel preTestLevel;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserPretestResult(String variable) {
        this(UserPretestResult.class, forVariable(variable), INITS);
    }

    public QUserPretestResult(Path<? extends UserPretestResult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPretestResult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPretestResult(PathMetadata metadata, PathInits inits) {
        this(UserPretestResult.class, metadata, inits);
    }

    public QUserPretestResult(Class<? extends UserPretestResult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningLanguage = inits.isInitialized("learningLanguage") ? new com.helloworld.backend_api.problem.domain.QLearningLanguage(forProperty("learningLanguage")) : null;
        this.preTestLevel = inits.isInitialized("preTestLevel") ? new com.helloworld.backend_api.pretest.domain.QPreTestLevel(forProperty("preTestLevel")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

