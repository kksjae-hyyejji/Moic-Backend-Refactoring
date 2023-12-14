package com.finp.moic.shop.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ShopRecommandRequest {

    @Positive
    private double latitude;

    @Positive
    private double longitude;

    @NotNull
    private List<CategoryRequest> categoryList;

    public ShopRecommandRequest() {
    }

    @Builder
    public ShopRecommandRequest(double latitude, double longitude,
                                @NotNull List<CategoryRequest> categoryList) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.categoryList = categoryList;
    }
}
