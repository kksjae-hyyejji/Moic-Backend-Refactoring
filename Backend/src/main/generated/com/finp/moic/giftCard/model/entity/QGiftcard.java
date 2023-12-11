package com.finp.moic.giftCard.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGiftcard is a Querydsl query type for Giftcard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGiftcard extends EntityPathBase<Giftcard> {

    private static final long serialVersionUID = -648116658L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGiftcard giftcard = new QGiftcard("giftcard");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    public final StringPath category = createString("category");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final ComparablePath<java.util.UUID> giftcardSeq = createComparable("giftcardSeq", java.util.UUID.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath mainCategory = createString("mainCategory");

    public final StringPath shopName = createString("shopName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.finp.moic.user.model.entity.QUser user;

    public QGiftcard(String variable) {
        this(Giftcard.class, forVariable(variable), INITS);
    }

    public QGiftcard(Path<? extends Giftcard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGiftcard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGiftcard(PathMetadata metadata, PathInits inits) {
        this(Giftcard.class, metadata, inits);
    }

    public QGiftcard(Class<? extends Giftcard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.finp.moic.user.model.entity.QUser(forProperty("user")) : null;
    }

}

