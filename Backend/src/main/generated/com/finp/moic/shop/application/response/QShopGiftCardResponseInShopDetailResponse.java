package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QShopGiftCardResponseInShopDetailResponse is a Querydsl Projection type for ShopGiftCardResponseInShopDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopGiftCardResponseInShopDetailResponse extends ConstructorExpression<ShopGiftCardResponseInShopDetailResponse> {

    private static final long serialVersionUID = 346248945L;

    public QShopGiftCardResponseInShopDetailResponse(com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<java.time.LocalDate> dueDate) {
        super(ShopGiftCardResponseInShopDetailResponse.class, new Class<?>[]{String.class, java.time.LocalDate.class}, imageUrl, dueDate);
    }

}

