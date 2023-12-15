package com.finp.moic.userBookmark.application.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserBookmarkDeleteServiceRequest {

    private List<ShopServiceRequest> shopList;

    public UserBookmarkDeleteServiceRequest() {
    }

    @Builder
    public UserBookmarkDeleteServiceRequest(List<ShopServiceRequest> shopList) {
        this.shopList = shopList;
    }
}
