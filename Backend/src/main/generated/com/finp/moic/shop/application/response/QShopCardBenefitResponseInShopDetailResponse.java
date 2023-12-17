package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QShopCardBenefitResponseInShopDetailResponse is a Querydsl Projection type for ShopCardBenefitResponseInShopDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopCardBenefitResponseInShopDetailResponse extends ConstructorExpression<ShopCardBenefitResponseInShopDetailResponse> {

    private static final long serialVersionUID = 67769874L;

    public QShopCardBenefitResponseInShopDetailResponse(com.querydsl.core.types.Expression<String> cardName, com.querydsl.core.types.Expression<String> cardImage, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> discount, com.querydsl.core.types.Expression<String> point, com.querydsl.core.types.Expression<String> cashBack) {
        super(ShopCardBenefitResponseInShopDetailResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class}, cardName, cardImage, content, discount, point, cashBack);
    }

}

