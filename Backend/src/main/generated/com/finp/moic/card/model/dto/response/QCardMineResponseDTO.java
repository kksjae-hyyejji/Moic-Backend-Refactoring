package com.finp.moic.card.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.model.dto.response.QCardMineResponseDTO is a Querydsl Projection type for CardMineResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardMineResponseDTO extends ConstructorExpression<CardMineResponseDTO> {

    private static final long serialVersionUID = -2048009942L;

    public QCardMineResponseDTO(com.querydsl.core.types.Expression<java.util.UUID> id, com.querydsl.core.types.Expression<String> company, com.querydsl.core.types.Expression<String> type, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> cardImage) {
        super(CardMineResponseDTO.class, new Class<?>[]{java.util.UUID.class, String.class, String.class, String.class, String.class}, id, company, type, name, cardImage);
    }

}

