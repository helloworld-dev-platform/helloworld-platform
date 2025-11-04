package com.helloworld.backend_api.stepup.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStepupMediumCategory is a Querydsl query type for StepupMediumCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStepupMediumCategory extends EntityPathBase<StepupMediumCategory> {

    private static final long serialVersionUID = -1712034371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStepupMediumCategory stepupMediumCategory = new QStepupMediumCategory("stepupMediumCategory");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QStepupLargeCategory largeCategoryId;

    public final StringPath mediumCategoryName = createString("mediumCategoryName");

    public final NumberPath<Integer> mediumCategoryOrder = createNumber("mediumCategoryOrder", Integer.class);

    public final ListPath<StepupSmallCategory, QStepupSmallCategory> steps = this.<StepupSmallCategory, QStepupSmallCategory>createList("steps", StepupSmallCategory.class, QStepupSmallCategory.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QStepupMediumCategory(String variable) {
        this(StepupMediumCategory.class, forVariable(variable), INITS);
    }

    public QStepupMediumCategory(Path<? extends StepupMediumCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStepupMediumCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStepupMediumCategory(PathMetadata metadata, PathInits inits) {
        this(StepupMediumCategory.class, metadata, inits);
    }

    public QStepupMediumCategory(Class<? extends StepupMediumCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.largeCategoryId = inits.isInitialized("largeCategoryId") ? new QStepupLargeCategory(forProperty("largeCategoryId"), inits.get("largeCategoryId")) : null;
    }

}

