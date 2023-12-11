package com.finp.moic.card.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCard is a Querydsl query type for Card
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCard extends EntityPathBase<Card> {

    private static final long serialVersionUID = 558716366L;

    public static final QCard card = new QCard("card");

    public final com.finp.moic.util.entity.QBase _super = new com.finp.moic.util.entity.QBase(this);

    public final ListPath<CardBenefit, QCardBenefit> cardBenefits = this.<CardBenefit, QCardBenefit>createList("cardBenefits", CardBenefit.class, QCardBenefit.class, PathInits.DIRECT2);

    public final StringPath cardImage = createString("cardImage");

    public final ComparablePath<java.util.UUID> cardSeq = createComparable("cardSeq", java.util.UUID.class);

    public final StringPath company = createString("company");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    //inherited
    public final BooleanPath isDelete = _super.isDelete;

    public final StringPath name = createString("name");

    public final StringPath type = createString("type");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCard(String variable) {
        super(Card.class, forVariable(variable));
    }

    public QCard(Path<? extends Card> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCard(PathMetadata metadata) {
        super(Card.class, metadata);
    }

}

