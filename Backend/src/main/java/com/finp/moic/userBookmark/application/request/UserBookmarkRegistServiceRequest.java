package com.finp.moic.userBookmark.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserBookmarkRegistServiceRequest {

    @NotBlank
    private String shopName;

    @NotNull
    private String shopLocation;

    public UserBookmarkRegistServiceRequest() {
    }

    @Builder
    public UserBookmarkRegistServiceRequest(String shopName, String shopLocation) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }
}
