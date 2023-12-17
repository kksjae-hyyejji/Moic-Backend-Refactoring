package com.finp.moic.shop.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopRecommandResponse {

    private String shopName;
    private String shopLocation;
    private String address;
    private double x;
    private double y;
    private List<ShopCardBenefitResponseInShopDetailResponse> benefits;
    private List<ShopGiftCardResponseInShopDetailResponse> gifts;

    public ShopRecommandResponse() {
    }

    @Builder
    @QueryProjection
    public ShopRecommandResponse(String shopName, String shopLocation,
                                 String address, double x, double y) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public void setBenefits(List<ShopCardBenefitResponseInShopDetailResponse> benefits) {
        this.benefits = benefits;
    }

    public void setGifts(List<ShopGiftCardResponseInShopDetailResponse> gifts) {
        this.gifts = gifts;
    }
}
