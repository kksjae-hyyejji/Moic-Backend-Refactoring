package com.finp.moic.userBookmark.adapter.in.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserBookmarkDeleteRequest {

    private List<ShopRequest> shopList;

    public UserBookmarkDeleteRequest() {
    }

    @Builder
    public UserBookmarkDeleteRequest(List<ShopRequest> shopList) {
        this.shopList = shopList;
    }
}
