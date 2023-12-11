package com.finp.moic.card.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCardBenefit is a Querydsl query type for CardBenefit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCardBenefit extends EntityPathBase<CardBenefit> {

    private static final long serialVersionUID = -283282711L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCardBenefit cardBenefit = new QCardBenefit("cardBenefit");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    public final QCard card;

    public final NumberPath<Long> cardBenefitSeq = createNumber("cardBenefitSeq", Long.class);

    public final StringPath cashback = createString("cashback");

    public final StringPath category = createString("category");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath discount = createString("discount");

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath point = createString("point");

    public final StringPath shopName = createString("shopName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCardBenefit(String variable) {
        this(CardBenefit.class, forVariable(variable), INITS);
    }

    public QCardBenefit(Path<? extends CardBenefit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCardBenefit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCardBenefit(PathMetadata metadata, PathInits inits) {
        this(CardBenefit.class, metadata, inits);
    }

    public QCardBenefit(Class<? extends CardBenefit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.card = inits.isInitialized("card") ? new QCard(forProperty("card")) : null;
    }

}

