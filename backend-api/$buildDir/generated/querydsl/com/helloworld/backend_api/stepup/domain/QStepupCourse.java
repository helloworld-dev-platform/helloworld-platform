package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupCourse is a Querydsl query type for StepupCourse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupCourse extends EntityPathBase<StepupCourse> {

    private static final long serialVersionUID = -911889339L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupCourse stepupCourse = new QStepupCourse("stepupCourse");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final StringPath courseName = createString("courseName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.helloworld.backend_api.problem.domain.QLearningLanguage learningLanguage;

    public final ListPath<StepupSection, QStepupSection> sections = this.<StepupSection, QStepupSection>createList("sections", StepupSection.class, QStepupSection.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupCourse(String variable) {
        this(StepupCourse.class, forVariable(variable), INITS);
    }

    public QStepupCourse(Path<? extends StepupCourse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupCourse(PathMetadata metadata, PathInits inits) {
        this(StepupCourse.class, metadata, inits);
    }

    public QStepupCourse(Class<? extends StepupCourse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningLanguage = inits.isInitialized("learningLanguage") ? new com.helloworld.backend_api.problem.domain.QLearningLanguage(forProperty("learningLanguage")) : null;
    }

}

