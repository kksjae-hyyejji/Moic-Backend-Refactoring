package com.finp.moic.user.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -174612860L;

    public static final QUser user = new QUser("user");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final ListPath<com.finp.moic.giftCard.model.entity.Giftcard, com.finp.moic.giftCard.model.entity.QGiftcard> giftCards = this.<com.finp.moic.giftCard.model.entity.Giftcard, com.finp.moic.giftCard.model.entity.QGiftcard>createList("giftCards", com.finp.moic.giftCard.model.entity.Giftcard.class, com.finp.moic.giftCard.model.entity.QGiftcard.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.finp.moic.userBookmark.model.entity.UserBookmark, com.finp.moic.userBookmark.model.entity.QUserBookmark> userBookMarks = this.<com.finp.moic.userBookmark.model.entity.UserBookmark, com.finp.moic.userBookmark.model.entity.QUserBookmark>createList("userBookMarks", com.finp.moic.userBookmark.model.entity.UserBookmark.class, com.finp.moic.userBookmark.model.entity.QUserBookmark.class, PathInits.DIRECT2);

    public final ListPath<com.finp.moic.card.model.entity.UserCard, com.finp.moic.card.model.entity.QUserCard> userCards = this.<com.finp.moic.card.model.entity.UserCard, com.finp.moic.card.model.entity.QUserCard>createList("userCards", com.finp.moic.card.model.entity.UserCard.class, com.finp.moic.card.model.entity.QUserCard.class, PathInits.DIRECT2);

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public final NumberPath<Integer> yearOfBirth = createNumber("yearOfBirth", Integer.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

