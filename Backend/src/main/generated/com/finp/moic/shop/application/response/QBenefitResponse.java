package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QBenefitResponse is a Querydsl Projection type for BenefitResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBenefitResponse extends ConstructorExpression<BenefitResponse> {

    private static final long serialVersionUID = -86685909L;

    public QBenefitResponse(com.querydsl.core.types.Expression<String> cardName, com.querydsl.core.types.Expression<String> cardImage, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> discount, com.querydsl.core.types.Expression<String> point, com.querydsl.core.types.Expression<String> cashBack) {
        super(BenefitResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class}, cardName, cardImage, content, discount, point, cashBack);
    }

}

