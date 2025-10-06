package com.helloworld.backend_api.pretest.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserPreTestSession is a Querydsl query type for UserPreTestSession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPreTestSession extends EntityPathBase<UserPreTestSession> {

    private static final long serialVersionUID = 1069877853L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserPreTestSession userPreTestSession = new QUserPreTestSession("userPreTestSession");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.helloworld.backend_api.problem.domain.QLearningLanguage learningLanguage;

    public final QPreTestLevel preTestLevel;

    public final DateTimePath<java.time.LocalDateTime> testCompletedAt = createDateTime("testCompletedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> testStartedAt = createDateTime("testStartedAt", java.time.LocalDateTime.class);

    public final com.helloworld.backend_api.user.domain.QUser user;

    public QUserPreTestSession(String variable) {
        this(UserPreTestSession.class, forVariable(variable), INITS);
    }

    public QUserPreTestSession(Path<? extends UserPreTestSession> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserPreTestSession(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserPreTestSession(PathMetadata metadata, PathInits inits) {
        this(UserPreTestSession.class, metadata, inits);
    }

    public QUserPreTestSession(Class<? extends UserPreTestSession> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningLanguage = inits.isInitialized("learningLanguage") ? new com.helloworld.backend_api.problem.domain.QLearningLanguage(forProperty("learningLanguage")) : null;
        this.preTestLevel = inits.isInitialized("preTestLevel") ? new QPreTestLevel(forProperty("preTestLevel")) : null;
        this.user = inits.isInitialized("user") ? new com.helloworld.backend_api.user.domain.QUser(forProperty("user")) : null;
    }

}

