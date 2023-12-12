package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.port.out.UserCardQueryPort;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.QCardMineServiceResponse;
import com.finp.moic.card.domain.QUserCard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserCardPersistencePortImpl implements UserCardQueryPort {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserCardPersistencePortImpl(JPAQueryFactory queryFactory) {
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
    public List<CardMineServiceResponse> findAllByUserId(String userId) {
        QUserCard userCard=QUserCard.userCard;

        return queryFactory
                .select(
                        new QCardMineServiceResponse(
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
