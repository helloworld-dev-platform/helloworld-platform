package com.helloworld.backend_api.problem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHint is a Querydsl query type for Hint
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHint extends EntityPathBase<Hint> {

    private static final long serialVersionUID = -155115294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHint hint = new QHint("hint");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> hintOrder = createNumber("hintOrder", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProblem problem;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QHint(String variable) {
        this(Hint.class, forVariable(variable), INITS);
    }

    public QHint(Path<? extends Hint> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHint(PathMetadata metadata, PathInits inits) {
        this(Hint.class, metadata, inits);
    }

    public QHint(Class<? extends Hint> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.problem = inits.isInitialized("problem") ? new QProblem(forProperty("problem"), inits.get("problem")) : null;
    }

}

