package com.helloworld.backend_api.achievement.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTier is a Querydsl query type for Tier
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTier extends EntityPathBase<Tier> {

    private static final long serialVersionUID = -891094835L;

    public static final QTier tier = new QTier("tier");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> tierMaxPercent = createNumber("tierMaxPercent", Double.class);

    public final NumberPath<Double> tierMinPercent = createNumber("tierMinPercent", Double.class);

    public final StringPath tierName = createString("tierName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTier(String variable) {
        super(Tier.class, forVariable(variable));
    }

    public QTier(Path<? extends Tier> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTier(PathMetadata metadata) {
        super(Tier.class, metadata);
    }

}

