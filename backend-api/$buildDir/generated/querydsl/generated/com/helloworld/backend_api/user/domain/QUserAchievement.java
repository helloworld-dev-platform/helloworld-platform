package com.helloworld.backend_api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserAchievement is a Querydsl query type for UserAchievement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserAchievement extends EntityPathBase<UserAchievement> {

    private static final long serialVersionUID = 1057941181L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserAchievement userAchievement = new QUserAchievement("userAchievement");

    public final com.helloworld.backend_api.common.domain.QBaseTimeEntity _super = new com.helloworld.backend_api.common.domain.QBaseTimeEntity(this);

    public final com.helloworld.backend_api.achievement.domain.QAchievement achieveId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> nowProgressLevel = createNumber("nowProgressLevel", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QUserAchievement(String variable) {
        this(UserAchievement.class, forVariable(variable), INITS);
    }

    public QUserAchievement(Path<? extends UserAchievement> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserAchievement(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserAchievement(PathMetadata metadata, PathInits inits) {
        this(UserAchievement.class, metadata, inits);
    }

    public QUserAchievement(Class<? extends UserAchievement> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.achieveId = inits.isInitialized("achieveId") ? new com.helloworld.backend_api.achievement.domain.QAchievement(forProperty("achieveId")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

