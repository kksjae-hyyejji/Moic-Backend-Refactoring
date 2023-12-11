package com.finp.moic.giftCard.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGiftcardBrand is a Querydsl query type for GiftcardBrand
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGiftcardBrand extends EntityPathBase<GiftcardBrand> {

    private static final long serialVersionUID = -2085186087L;

    public static final QGiftcardBrand giftcardBrand = new QGiftcardBrand("giftcardBrand");

    public final StringPath category = createString("category");

    public final NumberPath<Long> giftcardBrandSeq = createNumber("giftcardBrandSeq", Long.class);

    public final StringPath mainCategory = createString("mainCategory");

    public final StringPath name = createString("name");

    public QGiftcardBrand(String variable) {
        super(GiftcardBrand.class, forVariable(variable));
    }

    public QGiftcardBrand(Path<? extends GiftcardBrand> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGiftcardBrand(PathMetadata metadata) {
        super(GiftcardBrand.class, metadata);
    }

}

