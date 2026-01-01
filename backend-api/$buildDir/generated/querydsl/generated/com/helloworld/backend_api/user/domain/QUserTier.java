package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserTier is a Querydsl query type for UserTier
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserTier extends EntityPathBase<UserTier> {

    private static final long serialVersionUID = 774585876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserTier userTier = new QUserTier("userTier");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath seasonInfo = createString("seasonInfo");

    public final com.helloworld.backend_api.achievement.domain.QTier tierId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserTier(String variable) {
        this(UserTier.class, forVariable(variable), INITS);
    }

    public QUserTier(Path<? extends UserTier> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserTier(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserTier(PathMetadata metadata, PathInits inits) {
        this(UserTier.class, metadata, inits);
    }

    public QUserTier(Class<? extends UserTier> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.tierId = inits.isInitialized("tierId") ? new com.helloworld.backend_api.achievement.domain.QTier(forProperty("tierId")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

