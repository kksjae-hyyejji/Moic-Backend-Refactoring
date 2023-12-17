package com.finp.moic.shop.adapter.in.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopDetailRequest {

    String shopName;
    String shopLocation;

    public ShopDetailRequest() {
    }

    public ShopDetailRequest(String shopName, String shopLocation) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }
}
