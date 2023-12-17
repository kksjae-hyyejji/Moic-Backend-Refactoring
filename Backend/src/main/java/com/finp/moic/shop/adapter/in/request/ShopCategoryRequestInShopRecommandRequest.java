package com.finp.moic.shop.adapter.in.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopCategoryRequestInShopRecommandRequest {

    private String mainCategory;
    private String subCategory;

    public ShopCategoryRequestInShopRecommandRequest() {
    }

    @Builder
    public ShopCategoryRequestInShopRecommandRequest(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
