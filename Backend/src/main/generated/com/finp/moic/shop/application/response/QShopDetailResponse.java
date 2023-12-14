package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QShopDetailResponse is a Querydsl Projection type for ShopDetailResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopDetailResponse extends ConstructorExpression<ShopDetailResponse> {

    private static final long serialVersionUID = 88137269L;

    public QShopDetailResponse(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address) {
        super(ShopDetailResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class}, category, shopName, shopLocation, address);
    }

}

