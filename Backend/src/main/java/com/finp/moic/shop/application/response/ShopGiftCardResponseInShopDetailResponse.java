package com.finp.moic.shop.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class ShopGiftCardResponseInShopDetailResponse {

    private String imageUrl;
    private String dueDate;

    public ShopGiftCardResponseInShopDetailResponse() {
    }

    @QueryProjection
    @Builder

    public ShopGiftCardResponseInShopDetailResponse(String imageUrl, LocalDate dueDate) {
        this.imageUrl = imageUrl;
        this.dueDate = dueDate.toString();
    }
}
