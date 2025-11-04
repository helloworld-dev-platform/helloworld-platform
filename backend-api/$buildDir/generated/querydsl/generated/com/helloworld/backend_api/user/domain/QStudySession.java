package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudySession is a Querydsl query type for StudySession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudySession extends EntityPathBase<StudySession> {

    private static final long serialVersionUID = -1311320364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudySession studySession = new QStudySession("studySession");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath domainType = createString("domainType");

    public final NumberPath<Integer> durationSecond = createNumber("durationSecond", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> endTime = createDateTime("endTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.helloworld.backend_api.problem.domain.QProblem problem;

    public final DateTimePath<java.time.LocalDateTime> startTime = createDateTime("startTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QStudySession(String variable) {
        this(StudySession.class, forVariable(variable), INITS);
    }

    public QStudySession(Path<? extends StudySession> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudySession(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudySession(PathMetadata metadata, PathInits inits) {
        this(StudySession.class, metadata, inits);
    }

    public QStudySession(Class<? extends StudySession> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problem = inits.isInitialized("problem") ? new com.helloworld.backend_api.problem.domain.QProblem(forProperty("problem"), inits.get("problem")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

