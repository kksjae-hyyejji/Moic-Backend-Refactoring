package com.finp.moic.card.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.card.model.dto.response.QCardAllReponseDTO is a Querydsl Projection type for CardAllReponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCardAllReponseDTO extends ConstructorExpression<CardAllReponseDTO> {

    private static final long serialVersionUID = 231056977L;

    public QCardAllReponseDTO(com.querydsl.core.types.Expression<? extends java.util.List<String>> companyList, com.querydsl.core.types.Expression<? extends java.util.List<String>> typeList, com.querydsl.core.types.Expression<? extends java.util.List<CardResponseDTO>> cardList) {
        super(CardAllReponseDTO.class, new Class<?>[]{java.util.List.class, java.util.List.class, java.util.List.class}, companyList, typeList, cardList);
    }

}

