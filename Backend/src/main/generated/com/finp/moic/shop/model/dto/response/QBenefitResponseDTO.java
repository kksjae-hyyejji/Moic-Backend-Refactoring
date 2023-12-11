package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.model.dto.response.QBenefitResponseDTO is a Querydsl Projection type for BenefitResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBenefitResponseDTO extends ConstructorExpression<BenefitResponseDTO> {

    private static final long serialVersionUID = 452047742L;

    public QBenefitResponseDTO(com.querydsl.core.types.Expression<String> cardName, com.querydsl.core.types.Expression<String> cardImage, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> discount, com.querydsl.core.types.Expression<String> point, com.querydsl.core.types.Expression<String> cashBack) {
        super(BenefitResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, String.class, String.class}, cardName, cardImage, content, discount, point, cashBack);
    }

}

