package com.finp.moic.shop.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.application.response.QGiftResponse is a Querydsl Projection type for GiftResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftResponse extends ConstructorExpression<GiftResponse> {

    private static final long serialVersionUID = -271264450L;

    public QGiftResponse(com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<java.time.LocalDate> dueDate) {
        super(GiftResponse.class, new Class<?>[]{String.class, java.time.LocalDate.class}, imageUrl, dueDate);
    }

}

