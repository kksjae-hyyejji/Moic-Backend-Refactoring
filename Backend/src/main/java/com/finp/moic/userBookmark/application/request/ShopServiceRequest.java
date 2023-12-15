package com.finp.moic.userBookmark.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopServiceRequest {

    @NotBlank
    private String shopName;

    @NotNull
    private String shopLocation;

    public ShopServiceRequest() {
    }

    @Builder
    public ShopServiceRequest(String shopName, String shopLocation) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }
}
