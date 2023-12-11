package com.finp.moic.shop.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopRecommandRequestDTO {

    @Positive
    private double latitude;

    @Positive
    private double longitude;

    @NotNull
    private List<CategoryRequestDTO> categoryList;

    public ShopRecommandRequestDTO() {
    }

    @Builder
    public ShopRecommandRequestDTO(double latitude, double longitude,
                                   @NotNull List<CategoryRequestDTO> categoryList) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryList = categoryList;
    }
}
