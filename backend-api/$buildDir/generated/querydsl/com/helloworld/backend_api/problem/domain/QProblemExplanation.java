package com.helloworld.backend_api.problem.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProblemExplanation is a Querydsl query type for ProblemExplanation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProblemExplanation extends EntityPathBase<ProblemExplanation> {

    private static final long serialVersionUID = -1475432171L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProblemExplanation problemExplanation = new QProblemExplanation("problemExplanation");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final QChoice choice;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProblem problem;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QProblemExplanation(String variable) {
        this(ProblemExplanation.class, forVariable(variable), INITS);
    }

    public QProblemExplanation(Path<? extends ProblemExplanation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProblemExplanation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProblemExplanation(PathMetadata metadata, PathInits inits) {
        this(ProblemExplanation.class, metadata, inits);
    }

    public QProblemExplanation(Class<? extends ProblemExplanation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.choice = inits.isInitialized("choice") ? new QChoice(forProperty("choice"), inits.get("choice")) : null;
        this.problem = inits.isInitialized("problem") ? new QProblem(forProperty("problem"), inits.get("problem")) : null;
    }

}

