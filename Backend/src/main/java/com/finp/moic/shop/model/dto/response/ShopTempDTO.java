package com.finp.moic.shop.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopTempDTO implements Comparable<ShopTempDTO> {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private double distance;

    public ShopTempDTO() {
    }

    @Builder
    public ShopTempDTO(String category, String shopName, String shopLocation,
                       String address, double distance) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.distance = distance;
    }

    @Override
    public int compareTo(ShopTempDTO o) {
        return Double.compare(this.distance,o.distance);
    }
}
