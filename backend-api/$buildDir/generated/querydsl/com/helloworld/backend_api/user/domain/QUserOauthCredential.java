package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserOauthCredential is a Querydsl query type for UserOauthCredential
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserOauthCredential extends EntityPathBase<UserOauthCredential> {

    private static final long serialVersionUID = 1799201500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserOauthCredential userOauthCredential = new QUserOauthCredential("userOauthCredential");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath provider = createString("provider");

    public final StringPath providerId = createString("providerId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserOauthCredential(String variable) {
        this(UserOauthCredential.class, forVariable(variable), INITS);
    }

    public QUserOauthCredential(Path<? extends UserOauthCredential> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserOauthCredential(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserOauthCredential(PathMetadata metadata, PathInits inits) {
        this(UserOauthCredential.class, metadata, inits);
    }

    public QUserOauthCredential(Class<? extends UserOauthCredential> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

