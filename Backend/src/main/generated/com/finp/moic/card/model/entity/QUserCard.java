package com.finp.moic.card.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCard is a Querydsl query type for UserCard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCard extends EntityPathBase<UserCard> {

    private static final long serialVersionUID = 1554860345L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCard userCard = new QUserCard("userCard");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    public final QCard card;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.finp.moic.user.model.entity.QUser user;

    public final NumberPath<Long> userCardSeq = createNumber("userCardSeq", Long.class);

    public QUserCard(String variable) {
        this(UserCard.class, forVariable(variable), INITS);
    }

    public QUserCard(Path<? extends UserCard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCard(PathMetadata metadata, PathInits inits) {
        this(UserCard.class, metadata, inits);
    }

    public QUserCard(Class<? extends UserCard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.card = inits.isInitialized("card") ? new QCard(forProperty("card")) : null;
        this.user = inits.isInitialized("user") ? new com.finp.moic.user.model.entity.QUser(forProperty("user")) : null;
    }

}

