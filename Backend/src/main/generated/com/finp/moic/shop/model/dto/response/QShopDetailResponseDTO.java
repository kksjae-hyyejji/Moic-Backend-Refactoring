package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.model.dto.response.QShopDetailResponseDTO is a Querydsl Projection type for ShopDetailResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopDetailResponseDTO extends ConstructorExpression<ShopDetailResponseDTO> {

    private static final long serialVersionUID = -540208544L;

    public QShopDetailResponseDTO(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address) {
        super(ShopDetailResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class}, category, shopName, shopLocation, address);
    }

}

