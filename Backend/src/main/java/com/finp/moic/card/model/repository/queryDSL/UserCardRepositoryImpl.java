package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardMineResponseDTO;
import com.finp.moic.card.model.dto.response.QCardMineResponseDTO;
import com.finp.moic.card.model.dto.response.QCardResponseDTO;
import com.finp.moic.card.model.entity.Card;
import com.finp.moic.card.model.entity.QCard;
import com.finp.moic.card.model.entity.QCardBenefit;
import com.finp.moic.card.model.entity.QUserCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserCardRepositoryImpl implements UserCardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserCardRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Boolean exist(String userId, String cardName) {
        QUserCard userCard=QUserCard.userCard;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(userCard)
                .where(
                        userCard.user.id.eq(userId)
                        .and(userCard.card.name.eq(cardName))
                )
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<String> findAllCardNameByUserId(String userId) {
        QUserCard userCard=QUserCard.userCard;

        return queryFactory
                .select(userCard.card.name)
                .from(userCard)
                .where(userCard.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<CardMineResponseDTO> findAllByUserId(String userId) {
        QUserCard userCard=QUserCard.userCard;

        return queryFactory
                .select(
                        new QCardMineResponseDTO(
                                userCard.card.cardSeq.as("id"),
                                userCard.card.company,
                                userCard.card.type,
                                userCard.card.name,
                                userCard.card.cardImage
                        )
                )
                .from(userCard)
                .where(userCard.user.id.eq(userId))
                .fetch();
    }
}
