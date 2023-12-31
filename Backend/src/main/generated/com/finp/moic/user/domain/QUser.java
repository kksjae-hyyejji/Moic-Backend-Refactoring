package com.finp.moic.user.domain;

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

    private static final long serialVersionUID = -1201760312L;

    public static final QUser user = new QUser("user");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final ListPath<com.finp.moic.giftCard.domain.Giftcard, com.finp.moic.giftCard.domain.QGiftcard> giftCards = this.<com.finp.moic.giftCard.domain.Giftcard, com.finp.moic.giftCard.domain.QGiftcard>createList("giftCards", com.finp.moic.giftCard.domain.Giftcard.class, com.finp.moic.giftCard.domain.QGiftcard.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.finp.moic.userBookmark.domain.UserBookmark, com.finp.moic.userBookmark.domain.QUserBookmark> userBookMarks = this.<com.finp.moic.userBookmark.domain.UserBookmark, com.finp.moic.userBookmark.domain.QUserBookmark>createList("userBookMarks", com.finp.moic.userBookmark.domain.UserBookmark.class, com.finp.moic.userBookmark.domain.QUserBookmark.class, PathInits.DIRECT2);

    public final ListPath<com.finp.moic.card.domain.UserCard, com.finp.moic.card.domain.QUserCard> userCards = this.<com.finp.moic.card.domain.UserCard, com.finp.moic.card.domain.QUserCard>createList("userCards", com.finp.moic.card.domain.UserCard.class, com.finp.moic.card.domain.QUserCard.class, PathInits.DIRECT2);

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

