package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.model.dto.response.QShopSearchResponseDTO is a Querydsl Projection type for ShopSearchResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopSearchResponseDTO extends ConstructorExpression<ShopSearchResponseDTO> {

    private static final long serialVersionUID = 945412265L;

    public QShopSearchResponseDTO(com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> latitude, com.querydsl.core.types.Expression<Double> longitude) {
        super(ShopSearchResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, String.class, double.class, double.class}, category, shopName, shopLocation, address, latitude, longitude);
    }

}

