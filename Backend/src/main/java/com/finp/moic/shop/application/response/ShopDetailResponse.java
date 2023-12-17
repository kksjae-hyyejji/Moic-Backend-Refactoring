package com.finp.moic.shop.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopDetailResponse {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private boolean bookmark;
    private List<ShopCardBenefitResponseInShopDetailResponse> benefits;
    private List<ShopGiftCardResponseInShopDetailResponse> gifts;

    public ShopDetailResponse() {
    }

    @QueryProjection
    @Builder
    public ShopDetailResponse(String category, String shopName, String shopLocation,
                              String address) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.bookmark=false;
    }

    public void setBookmark(boolean bookmark) { this.bookmark = bookmark; }

    public void setBenefits(List<ShopCardBenefitResponseInShopDetailResponse> benefits){
        this.benefits=benefits;
    }

    public void setGifts(List<ShopGiftCardResponseInShopDetailResponse> gifts){
        this.gifts=gifts;
    }
}
