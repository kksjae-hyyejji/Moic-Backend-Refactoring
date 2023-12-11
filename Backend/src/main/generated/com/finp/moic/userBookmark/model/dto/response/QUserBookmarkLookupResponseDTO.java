package com.finp.moic.userBookmark.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.userBookmark.model.dto.response.QUserBookmarkLookupResponseDTO is a Querydsl Projection type for UserBookmarkLookupResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QUserBookmarkLookupResponseDTO extends ConstructorExpression<UserBookmarkLookupResponseDTO> {

    private static final long serialVersionUID = 439418625L;

    public QUserBookmarkLookupResponseDTO(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> latitude, com.querydsl.core.types.Expression<Double> longitude) {
        super(UserBookmarkLookupResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, double.class, double.class}, category, shopName, shopLocation, address, latitude, longitude);
    }

}

