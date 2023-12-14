package com.finp.moic.shop.application.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopTempResponse implements Comparable<ShopTempResponse> {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private double distance;

    public ShopTempResponse() {
    }

    @Builder
    public ShopTempResponse(String category, String shopName, String shopLocation,
                            String address, double distance) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.distance = distance;
    }

    @Override
    public int compareTo(ShopTempResponse o) {
        return Double.compare(this.distance,o.distance);
    }
}
