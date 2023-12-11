package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopRecommandResponseDTO {

    private String shopName;
    private String shopLocation;
    private String address;
    private double x;
    private double y;
    private List<BenefitResponseDTO> benefits;
    private List<GiftResponseDTO> gifts;

    public ShopRecommandResponseDTO() {
    }

    @Builder
    @QueryProjection
    public ShopRecommandResponseDTO(String shopName, String shopLocation,
                                    String address, double x, double y) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.x = x;
        this.y = y;
    }

    public void setBenefits(List<BenefitResponseDTO> benefits) {
        this.benefits = benefits;
    }

    public void setGifts(List<GiftResponseDTO> gifts) {
        this.gifts = gifts;
    }
}
