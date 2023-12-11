package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.finp.moic.shop.model.dto.response.QShopRecommandResponseDTO is a Querydsl Projection type for ShopRecommandResponseDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QShopRecommandResponseDTO extends ConstructorExpression<ShopRecommandResponseDTO> {

    private static final long serialVersionUID = 363227795L;

    public QShopRecommandResponseDTO(com.querydsl.core.types.Expression<String> shopName, com.querydsl.core.types.Expression<String> shopLocation, com.querydsl.core.types.Expression<String> address, com.querydsl.core.types.Expression<Double> x, com.querydsl.core.types.Expression<Double> y) {
        super(ShopRecommandResponseDTO.class, new Class<?>[]{String.class, String.class, String.class, double.class, double.class}, shopName, shopLocation, address, x, y);
    }

}

