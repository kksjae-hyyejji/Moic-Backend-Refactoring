package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopSearchResponseDTO {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private boolean bookmark;
    private double latitude;
    private double longitude;
    private boolean benefits;
    private boolean gifts;

    public ShopSearchResponseDTO() {
    }

    @QueryProjection
    @Builder
    public ShopSearchResponseDTO(String category, String shopName,
                                 String shopLocation, String address,
                                 double latitude, double longitude) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.bookmark=false;
        this.latitude = latitude;
        this.longitude = longitude;
        this.benefits=false;
        this.gifts=false;
    }

    public void setBookmark(boolean bookmark) { this.bookmark = bookmark; }

    public void setBenefits(boolean flag){
        this.benefits=flag;
    }

    public void setGifts(boolean flag){
        this.gifts=flag;
    }
}
