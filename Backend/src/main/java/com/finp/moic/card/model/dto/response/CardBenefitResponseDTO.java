package com.finp.moic.card.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class CardBenefitResponseDTO implements Serializable {

    private String category;
    private String shopName;
    private String content;
    private String discount;
    private String point;
    private String cashBack;

    public CardBenefitResponseDTO() {
    }

    @QueryProjection
    @Builder
    public CardBenefitResponseDTO(String category, String shopName, String content,
                                  String discount, String point, String cashBack) {
        this.category = category;
        this.shopName = shopName;
        this.content = content;
        this.discount = discount;
        this.point = point;
        this.cashBack = cashBack;
    }
}
