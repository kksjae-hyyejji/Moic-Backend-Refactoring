package com.finp.moic.shop.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = -822728678L;

    public static final QShop shop = new QShop("shop");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    public final StringPath address = createString("address");

    public final StringPath category = createString("category");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath guName = createString("guName");

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final StringPath location = createString("location");

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath mainCategory = createString("mainCategory");

    public final StringPath name = createString("name");

    public final NumberPath<Long> shopSeq = createNumber("shopSeq", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<com.finp.moic.userBookmark.model.entity.UserBookmark, com.finp.moic.userBookmark.model.entity.QUserBookmark> userBookMarks = this.<com.finp.moic.userBookmark.model.entity.UserBookmark, com.finp.moic.userBookmark.model.entity.QUserBookmark>createList("userBookMarks", com.finp.moic.userBookmark.model.entity.UserBookmark.class, com.finp.moic.userBookmark.model.entity.QUserBookmark.class, PathInits.DIRECT2);

    public QShop(String variable) {
        super(Shop.class, forVariable(variable));
    }

    public QShop(Path<? extends Shop> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShop(PathMetadata metadata) {
        super(Shop.class, metadata);
    }

}

