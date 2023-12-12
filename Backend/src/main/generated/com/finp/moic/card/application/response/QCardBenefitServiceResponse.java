package com.finp.moic.card.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.application.response.QCardBenefitServiceResponse is a Querydsl Projection type for CardBenefitServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardBenefitServiceResponse extends ConstructorExpression<CardBenefitServiceResponse> {

    private static final long serialVersionUID = 1308593334L;

    public QCardBenefitServiceResponse(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> discount, com.querydsl.core.types.Expression<String> point, com.querydsl.core.types.Expression<String> cashBack) {
        super(CardBenefitServiceResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class}, category, shopName, content, discount, point, cashBack);
    }

}

