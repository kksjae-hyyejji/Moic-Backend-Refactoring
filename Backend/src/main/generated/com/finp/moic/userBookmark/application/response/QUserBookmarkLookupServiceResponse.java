package com.finp.moic.userBookmark.application.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.userBookmark.application.response.QUserBookmarkLookupServiceResponse is a Querydsl Projection type for UserBookmarkLookupServiceResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserBookmarkLookupServiceResponse extends ConstructorExpression<UserBookmarkLookupServiceResponse> {

    private static final long serialVersionUID = -390232445L;

    public QUserBookmarkLookupServiceResponse(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> latitude, com.querydsl.core.types.Expression<Double> longitude) {
        super(UserBookmarkLookupServiceResponse.class, new Class<?>[]{String.class, String.class, String.class, String.class, double.class, double.class}, category, shopName, shopLocation, address, latitude, longitude);
    }

}

