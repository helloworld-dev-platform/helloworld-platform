package com.helloworld.backend_api.problem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLearningLanguage is a Querydsl query type for LearningLanguage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLearningLanguage extends EntityPathBase<LearningLanguage> {

    private static final long serialVersionUID = 976486001L;

    public static final QLearningLanguage learningLanguage = new QLearningLanguage("learningLanguage");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath languageName = createString("languageName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QLearningLanguage(String variable) {
        super(LearningLanguage.class, forVariable(variable));
    }

    public QLearningLanguage(Path<? extends LearningLanguage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLearningLanguage(PathMetadata metadata) {
        super(LearningLanguage.class, metadata);
    }

}

