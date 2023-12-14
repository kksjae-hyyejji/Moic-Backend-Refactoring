package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QShopRecommandResponse is a Querydsl Projection type for ShopRecommandResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopRecommandResponse extends ConstructorExpression<ShopRecommandResponse> {

    private static final long serialVersionUID = 1347739830L;

    public QShopRecommandResponse(com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> x, com.querydsl.core.types.Expression<Double> y) {
        super(ShopRecommandResponse.class, new Class<?>[]{String.class, String.class, String.class, double.class, double.class}, shopName, shopLocation, address, x, y);
    }

}

