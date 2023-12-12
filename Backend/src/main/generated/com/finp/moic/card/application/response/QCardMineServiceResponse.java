package com.finp.moic.card.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.application.response.QCardMineServiceResponse is a Querydsl Projection type for CardMineServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardMineServiceResponse extends ConstructorExpression<CardMineServiceResponse> {

    private static final long serialVersionUID = -1028386708L;

    public QCardMineServiceResponse(com.querydsl.core.types.Expression<java.util.UUID> id, com.querydsl.core.types.Expression<String> company, com.querydsl.core.types.Expression<String> type, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> cardImage) {
        super(CardMineServiceResponse.class, new Class<?>[]{java.util.UUID.class, String.class, String.class, String.class, String.class}, id, company, type, name, cardImage);
    }

}

