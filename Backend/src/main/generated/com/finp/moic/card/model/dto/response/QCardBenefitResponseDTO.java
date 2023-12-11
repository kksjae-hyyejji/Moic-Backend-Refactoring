package com.finp.moic.card.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.model.dto.response.QCardBenefitResponseDTO is a Querydsl Projection type for CardBenefitResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardBenefitResponseDTO extends ConstructorExpression<CardBenefitResponseDTO> {

    private static final long serialVersionUID = 910226120L;

    public QCardBenefitResponseDTO(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> discount, com.querydsl.core.types.Expression<String> point, com.querydsl.core.types.Expression<String> cashBack) {
        super(CardBenefitResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class}, category, shopName, content, discount, point, cashBack);
    }

}

