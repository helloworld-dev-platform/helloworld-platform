package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupLargeCategory is a Querydsl query type for StepupLargeCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupLargeCategory extends EntityPathBase<StepupLargeCategory> {

    private static final long serialVersionUID = -186088977L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupLargeCategory stepupLargeCategory = new QStepupLargeCategory("stepupLargeCategory");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final EnumPath<com.helloworld.backend_api.problem.domain.Difficulty> difficulty = createEnum("difficulty", com.helloworld.backend_api.problem.domain.Difficulty.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath largeCategoryName = createString("largeCategoryName");

    public final com.helloworld.backend_api.problem.domain.QLearningLanguage learningLanguage;

    public final ListPath<StepupMediumCategory, QStepupMediumCategory> sections = this.<StepupMediumCategory, QStepupMediumCategory>createList("sections", StepupMediumCategory.class, QStepupMediumCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupLargeCategory(String variable) {
        this(StepupLargeCategory.class, forVariable(variable), INITS);
    }

    public QStepupLargeCategory(Path<? extends StepupLargeCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupLargeCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupLargeCategory(PathMetadata metadata, PathInits inits) {
        this(StepupLargeCategory.class, metadata, inits);
    }

    public QStepupLargeCategory(Class<? extends StepupLargeCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.learningLanguage = inits.isInitialized("learningLanguage") ? new com.helloworld.backend_api.problem.domain.QLearningLanguage(forProperty("learningLanguage")) : null;
    }

}

