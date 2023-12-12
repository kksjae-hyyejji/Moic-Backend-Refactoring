package com.finp.moic.giftCard.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.giftCard.application.response.QGiftcardListServiceResponse is a Querydsl Projection type for GiftcardListServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftcardListServiceResponse extends ConstructorExpression<GiftcardListServiceResponse> {

    private static final long serialVersionUID = -98660159L;

    public QGiftcardListServiceResponse(com.querydsl.core.types.Expression<java.util.UUID> id, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<java.time.LocalDate> dueDate) {
        super(GiftcardListServiceResponse.class, new Class<?>[]{java.util.UUID.class, String.class, java.time.LocalDate.class}, id, imageUrl, dueDate);
    }

}

