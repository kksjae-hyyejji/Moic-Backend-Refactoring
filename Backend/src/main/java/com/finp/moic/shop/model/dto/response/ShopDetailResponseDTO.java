package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopDetailResponseDTO {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private boolean bookmark;
    private List<BenefitResponseDTO> benefits;
    private List<GiftResponseDTO> gifts;

    public ShopDetailResponseDTO() {
    }

    @QueryProjection
    @Builder
    public ShopDetailResponseDTO(String category, String shopName, String shopLocation,
                                 String address) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.bookmark=false;
    }

    public void setBookmark(boolean bookmark) { this.bookmark = bookmark; }

    public void setBenefits(List<BenefitResponseDTO> benefits){
        this.benefits=benefits;
    }

    public void setGifts(List<GiftResponseDTO> gifts){
        this.gifts=gifts;
    }
}
