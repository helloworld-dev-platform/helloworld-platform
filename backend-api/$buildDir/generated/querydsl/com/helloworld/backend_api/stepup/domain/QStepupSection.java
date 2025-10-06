package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupSection is a Querydsl query type for StepupSection
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupSection extends EntityPathBase<StepupSection> {

    private static final long serialVersionUID = -1486473221L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupSection stepupSection = new QStepupSection("stepupSection");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath sectionName = createString("sectionName");

    public final NumberPath<Integer> sectionOrder = createNumber("sectionOrder", Integer.class);

    public final ListPath<StepupStep, QStepupStep> steps = this.<StepupStep, QStepupStep>createList("steps", StepupStep.class, QStepupStep.class, PathInits.DIRECT2);

    public final QStepupCourse stepupCourse;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupSection(String variable) {
        this(StepupSection.class, forVariable(variable), INITS);
    }

    public QStepupSection(Path<? extends StepupSection> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupSection(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupSection(PathMetadata metadata, PathInits inits) {
        this(StepupSection.class, metadata, inits);
    }

    public QStepupSection(Class<? extends StepupSection> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.stepupCourse = inits.isInitialized("stepupCourse") ? new QStepupCourse(forProperty("stepupCourse"), inits.get("stepupCourse")) : null;
    }

}

