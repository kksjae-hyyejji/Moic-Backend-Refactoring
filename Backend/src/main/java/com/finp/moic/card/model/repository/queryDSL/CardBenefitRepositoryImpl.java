package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardBenefitResponseDTO;
import com.finp.moic.card.model.dto.response.QCardBenefitResponseDTO;
import com.finp.moic.card.model.entity.CardBenefit;
import com.finp.moic.card.model.entity.QCard;
import com.finp.moic.card.model.entity.QCardBenefit;
import com.finp.moic.card.model.entity.QUserCard;
import com.finp.moic.shop.model.dto.response.BenefitResponseDTO;
import com.finp.moic.shop.model.dto.response.QBenefitResponseDTO;
import com.finp.moic.shop.model.entity.QShop;
import com.finp.moic.user.model.entity.QUser;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardBenefitRepositoryImpl implements CardBenefitRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public CardBenefitRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Boolean exist(String cardName){
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(cardBenefit)
                .where(cardBenefit.card.name.eq(cardName))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<CardBenefitResponseDTO> findByCardName(String cardName) {
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;

        return queryFactory
                .select(
                        new QCardBenefitResponseDTO(
                                cardBenefit.category,
                                cardBenefit.shopName,
                                cardBenefit.content,
                                cardBenefit.discount,
                                cardBenefit.point,
                                cardBenefit.cashback
                        )
                )
                .from(cardBenefit)
                .where(cardBenefit.card.name.eq(cardName))
                .fetch();
    }

    @Override
    public List<BenefitResponseDTO> findAllByUserIdAndShopName(String userId, String shopName) {
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;
        QUserCard userCard=QUserCard.userCard;

        return queryFactory
                .select(
                        new QBenefitResponseDTO(
                                cardBenefit.card.name.as("cardName"),
                                cardBenefit.card.cardImage.as("cardImage"),
                                cardBenefit.content,
                                cardBenefit.discount,
                                cardBenefit.point,
                                cardBenefit.cashback
                        )
                )
                .from(cardBenefit)
                .where(
                        cardBenefit.card.in(
                                JPAExpressions.select(userCard.card)
                                        .from(userCard)
                                        .where(userCard.user.id.eq(userId))
                        )
                                        .and(cardBenefit.shopName.eq(shopName))
                )
                .fetch();
    }

    @Override
    public List<String> findAllShopNameByUserId(String userId) {
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;
        QUserCard userCard=QUserCard.userCard;
        QShop shop=QShop.shop;

        return queryFactory
                .selectDistinct(cardBenefit.shopName.coalesce(shop.name)) //coalesce? 가져오는 값이 null일 경우 괄호 안의 값을 가져올 수 있다.
                .from(cardBenefit)
                .innerJoin(userCard)
                .on(cardBenefit.card.name.eq(userCard.card.name))
                .innerJoin(shop)
                .on(cardBenefit.category.eq(shop.category))
                .where(userCard.user.id.eq(userId))
                .fetch();
    }
}
