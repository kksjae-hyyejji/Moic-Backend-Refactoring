package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.model.dto.response.QGiftResponseDTO is a Querydsl Projection type for GiftResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftResponseDTO extends ConstructorExpression<GiftResponseDTO> {

    private static final long serialVersionUID = -421412041L;

    public QGiftResponseDTO(com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<java.time.LocalDate> dueDate) {
        super(GiftResponseDTO.class, new Class<?>[]{String.class, java.time.LocalDate.class}, imageUrl, dueDate);
    }

}

