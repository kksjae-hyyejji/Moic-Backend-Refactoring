package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QShopSearchResponse is a Querydsl Projection type for ShopSearchResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopSearchResponse extends ConstructorExpression<ShopSearchResponse> {

    private static final long serialVersionUID = -2022316916L;

    public QShopSearchResponse(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> latitude, com.querydsl.core.types.Expression<Double> longitude) {
        super(ShopSearchResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, double.class, double.class}, category, shopName, shopLocation, address, latitude, longitude);
    }

}

