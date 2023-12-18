package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.response.CardBenefitServiceResponse;
import com.finp.moic.card.application.response.QCardBenefitServiceResponse;
import com.finp.moic.card.domain.QCardBenefit;
import com.finp.moic.card.domain.QUserCard;
import com.finp.moic.shop.application.response.QShopCardBenefitResponseInShopDetailResponse;
import com.finp.moic.shop.application.response.ShopCardBenefitResponseInShopDetailResponse;
import com.finp.moic.shop.domain.QShop;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardBenefitQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public Boolean exist(String cardName){
        QCardBenefit cardBenefit= QCardBenefit.cardBenefit;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(cardBenefit)
                .where(cardBenefit.card.name.eq(cardName))
                .fetchFirst();

        return fetchOne != null;
    }

    public List<CardBenefitServiceResponse> findByCardName(String cardName) {
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;

        return queryFactory
                .select(
                        new QCardBenefitServiceResponse(
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

    public List<ShopCardBenefitResponseInShopDetailResponse> findAllByUserIdAndShopName(String userId, String shopName) {
        QCardBenefit cardBenefit=QCardBenefit.cardBenefit;
        QUserCard userCard=QUserCard.userCard;

        return queryFactory
                .select(
                        new QShopCardBenefitResponseInShopDetailResponse(
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
