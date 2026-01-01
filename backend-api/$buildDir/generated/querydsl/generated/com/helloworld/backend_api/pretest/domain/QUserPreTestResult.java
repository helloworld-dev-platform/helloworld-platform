package com.helloworld.backend_api.pretest.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserPreTestResult is a Querydsl query type for UserPreTestResult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserPreTestResult extends EntityPathBase<UserPreTestResult> {

    private static final long serialVersionUID = -271209610L;

    public static final QUserPreTestResult userPreTestResult = new QUserPreTestResult("userPreTestResult");

    public final DateTimePath<java.time.LocalDateTime> completedAt = createDateTime("completedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> learningLanguageId = createNumber("learningLanguageId", Long.class);

    public final NumberPath<Long> preTestLevelId = createNumber("preTestLevelId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserPreTestResult(String variable) {
        super(UserPreTestResult.class, forVariable(variable));
    }

    public QUserPreTestResult(Path<? extends UserPreTestResult> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserPreTestResult(PathMetadata metadata) {
        super(UserPreTestResult.class, metadata);
    }

}

