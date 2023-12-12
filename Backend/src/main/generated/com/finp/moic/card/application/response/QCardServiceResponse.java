package com.finp.moic.card.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.application.response.QCardServiceResponse is a Querydsl Projection type for CardServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardServiceResponse extends ConstructorExpression<CardServiceResponse> {

    private static final long serialVersionUID = -39319105L;

    public QCardServiceResponse(com.querydsl.core.types.Expression<java.util.UUID> id, com.querydsl.core.types.Expression<String> company, com.querydsl.core.types.Expression<String> type, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> cardImage) {
        super(CardServiceResponse.class, new Class<?>[]{java.util.UUID.class, String.class, String.class, String.class, String.class}, id, company, type, name, cardImage);
    }

}

