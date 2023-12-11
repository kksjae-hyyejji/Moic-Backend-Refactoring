package com.finp.moic.userBookmark.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserBookmark is a Querydsl query type for UserBookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserBookmark extends EntityPathBase<UserBookmark> {

    private static final long serialVersionUID = 598187184L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserBookmark userBookmark = new QUserBookmark("userBookmark");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final com.finp.moic.shop.model.entity.QShop shop;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.finp.moic.user.model.entity.QUser user;

    public final NumberPath<Long> userBookmarkSeq = createNumber("userBookmarkSeq", Long.class);

    public QUserBookmark(String variable) {
        this(UserBookmark.class, forVariable(variable), INITS);
    }

    public QUserBookmark(Path<? extends UserBookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserBookmark(PathMetadata metadata, PathInits inits) {
        this(UserBookmark.class, metadata, inits);
    }

    public QUserBookmark(Class<? extends UserBookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.shop = inits.isInitialized("shop") ? new com.finp.moic.shop.model.entity.QShop(forProperty("shop")) : null;
        this.user = inits.isInitialized("user") ? new com.finp.moic.user.model.entity.QUser(forProperty("user")) : null;
    }

}

