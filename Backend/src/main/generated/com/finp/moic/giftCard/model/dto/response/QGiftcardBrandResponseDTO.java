package com.finp.moic.giftCard.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.giftCard.model.dto.response.QGiftcardBrandResponseDTO is a Querydsl Projection type for GiftcardBrandResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftcardBrandResponseDTO extends ConstructorExpression<GiftcardBrandResponseDTO> {

    private static final long serialVersionUID = -1729493736L;

    public QGiftcardBrandResponseDTO(com.querydsl.core.types.Expression<String> mainCategory, com.querydsl.core.types.Expression<String> category) {
        super(GiftcardBrandResponseDTO.class, new Class<?>[]{String.class, String.class}, mainCategory, category);
    }

}

