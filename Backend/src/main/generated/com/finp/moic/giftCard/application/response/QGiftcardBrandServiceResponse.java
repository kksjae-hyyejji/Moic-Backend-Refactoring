package com.finp.moic.giftCard.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.giftCard.application.response.QGiftcardBrandServiceResponse is a Querydsl Projection type for GiftcardBrandServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftcardBrandServiceResponse extends ConstructorExpression<GiftcardBrandServiceResponse> {

    private static final long serialVersionUID = -1646867066L;

    public QGiftcardBrandServiceResponse(com.querydsl.core.types.Expression<String> mainCategory, com.querydsl.core.types.Expression<String> category) {
        super(GiftcardBrandServiceResponse.class, new Class<?>[]{String.class, String.class}, mainCategory, category);
    }

}

