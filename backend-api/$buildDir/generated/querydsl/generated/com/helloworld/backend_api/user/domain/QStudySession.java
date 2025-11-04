package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudySession is a Querydsl query type for StudySession
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStudySession extends EntityPathBase<StudySession> {

    private static final long serialVersionUID = -1311320364L;

    public static final QStudySession studySession = new QStudySession("studySession");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QStudySession(String variable) {
        super(StudySession.class, forVariable(variable));
    }

    public QStudySession(Path<? extends StudySession> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudySession(PathMetadata metadata) {
        super(StudySession.class, metadata);
    }

}

