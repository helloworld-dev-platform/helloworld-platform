package com.helloworld.backend_api.pretest.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPreTestLevel is a Querydsl query type for PreTestLevel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPreTestLevel extends EntityPathBase<PreTestLevel> {

    private static final long serialVersionUID = 913351200L;

    public static final QPreTestLevel preTestLevel = new QPreTestLevel("preTestLevel");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath preTestEndMsg = createString("preTestEndMsg");

    public final StringPath preTestLevelName = createString("preTestLevelName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPreTestLevel(String variable) {
        super(PreTestLevel.class, forVariable(variable));
    }

    public QPreTestLevel(Path<? extends PreTestLevel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPreTestLevel(PathMetadata metadata) {
        super(PreTestLevel.class, metadata);
    }

}

