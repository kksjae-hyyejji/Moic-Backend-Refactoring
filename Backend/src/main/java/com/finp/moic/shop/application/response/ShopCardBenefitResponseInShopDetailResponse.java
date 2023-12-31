package com.finp.moic.shop.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopCardBenefitResponseInShopDetailResponse {

    private String cardName;
    private String cardImage;
    private String content;
    private String discount;
    private String point;
    private String cashBack;

    public ShopCardBenefitResponseInShopDetailResponse() {
    }

    @QueryProjection
    @Builder
    public ShopCardBenefitResponseInShopDetailResponse(String cardName, String cardImage, String content,
                                                       String discount, String point, String cashBack) {
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.content = content;
        this.discount = discount;
        this.point = point;
        this.cashBack = cashBack;
    }
}
