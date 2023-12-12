package com.finp.moic.card.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.application.response.QCardAllServiceReponse is a Querydsl Projection type for CardAllServiceReponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardAllServiceReponse extends ConstructorExpression<CardAllServiceReponse> {

    private static final long serialVersionUID = 716992387L;

    public QCardAllServiceReponse(com.querydsl.core.types.Expression<? extends java.util.List<String>> companyList, com.querydsl.core.types.Expression<? extends java.util.List<String>> typeList, com.querydsl.core.types.Expression<? extends java.util.List<CardServiceResponse>> cardList) {
        super(CardAllServiceReponse.class, new Class<?>[]{java.util.List.class, java.util.List.class, java.util.List.class}, companyList, typeList, cardList);
    }

}

