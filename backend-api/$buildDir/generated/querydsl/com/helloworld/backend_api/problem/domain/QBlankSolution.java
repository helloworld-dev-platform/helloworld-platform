package com.helloworld.backend_api.problem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlankSolution is a Querydsl query type for BlankSolution
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlankSolution extends EntityPathBase<BlankSolution> {

    private static final long serialVersionUID = 1969742578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlankSolution blankSolution = new QBlankSolution("blankSolution");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final NumberPath<Integer> blankOrder = createNumber("blankOrder", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProblem problemId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QBlankSolution(String variable) {
        this(BlankSolution.class, forVariable(variable), INITS);
    }

    public QBlankSolution(Path<? extends BlankSolution> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlankSolution(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlankSolution(PathMetadata metadata, PathInits inits) {
        this(BlankSolution.class, metadata, inits);
    }

    public QBlankSolution(Class<? extends BlankSolution> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problemId = inits.isInitialized("problemId") ? new QProblem(forProperty("problemId"), inits.get("problemId")) : null;
    }

}

