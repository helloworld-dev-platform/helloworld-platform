package com.helloworld.backend_api.point.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPointPolicy is a Querydsl query type for PointPolicy
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPointPolicy extends EntityPathBase<PointPolicy> {

    private static final long serialVersionUID = -113679978L;

    public static final QPointPolicy pointPolicy = new QPointPolicy("pointPolicy");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final NumberPath<Integer> pointAmount = createNumber("pointAmount", Integer.class);

    public final StringPath pointDescription = createString("pointDescription");

    public final StringPath transactionType = createString("transactionType");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPointPolicy(String variable) {
        super(PointPolicy.class, forVariable(variable));
    }

    public QPointPolicy(Path<? extends PointPolicy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPointPolicy(PathMetadata metadata) {
        super(PointPolicy.class, metadata);
    }

}

