package com.finp.moic.userBookmark.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ShopRequestDTO {

    @NotBlank
    private String shopName;

    @NotNull
    private String shopLocation;

    public ShopRequestDTO() {
    }

    @Builder
    public ShopRequestDTO(String shopName, String shopLocation) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }
}
