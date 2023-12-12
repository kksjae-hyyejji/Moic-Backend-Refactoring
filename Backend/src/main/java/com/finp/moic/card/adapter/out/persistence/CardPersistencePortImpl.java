package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.port.out.CardQueryPort;
import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.card.application.response.QCardServiceResponse;
import com.finp.moic.card.domain.QCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardPersistencePortImpl implements CardQueryPort {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public CardPersistencePortImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Boolean exist(String cardName) {
        QCard card=QCard.card;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(card)
                .where(card.name.eq(cardName))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<CardServiceResponse> search(String company, String type, String cardName) {
        QCard card=QCard.card;

        return queryFactory
                .select(
                        new QCardServiceResponse(
                                card.cardSeq.as("id"),
                                card.company,
                                card.type,
                                card.name,
                                card.cardImage.as("cardImage")
                        )
                )
                .from(card)
                .where(
                        card.company.contains(company)
                                .and(card.type.contains(type))
                                .and(card.name.contains(cardName))
                )
                .fetch();
    }

    @Override
    public List<CardServiceResponse> findAllCard() {
        QCard card=QCard.card;

        return queryFactory
                .select(
                        new QCardServiceResponse(
                                card.cardSeq.as("id"),
                                card.company,
                                card.type,
                                card.name,
                                card.cardImage.as("cardImage")
                        )
                )
                .from(card)
                .fetch();
    }
}
