package com.finp.moic.giftCard.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.giftCard.model.dto.response.QGiftcardListResponseDTO is a Querydsl Projection type for GiftcardListResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGiftcardListResponseDTO extends ConstructorExpression<GiftcardListResponseDTO> {

    private static final long serialVersionUID = 1561464063L;

    public QGiftcardListResponseDTO(com.querydsl.core.types.Expression<java.util.UUID> id, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<java.time.LocalDate> dueDate) {
        super(GiftcardListResponseDTO.class, new Class<?>[]{java.util.UUID.class, String.class, java.time.LocalDate.class}, id, imageUrl, dueDate);
    }

}

