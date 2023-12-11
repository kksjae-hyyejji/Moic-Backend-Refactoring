package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardResponseDTO;
import com.finp.moic.card.model.dto.response.QCardResponseDTO;
import com.finp.moic.card.model.entity.Card;
import com.finp.moic.card.model.entity.QCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardRepositoryImpl implements CardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public CardRepositoryImpl(JPAQueryFactory queryFactory) {
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
    public List<CardResponseDTO> search(String company, String type, String cardName) {
        QCard card=QCard.card;

        return queryFactory
                .select(
                        new QCardResponseDTO(
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
    public List<CardResponseDTO> findAllCard() {
        QCard card=QCard.card;

        return queryFactory
                .select(
                        new QCardResponseDTO(
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
