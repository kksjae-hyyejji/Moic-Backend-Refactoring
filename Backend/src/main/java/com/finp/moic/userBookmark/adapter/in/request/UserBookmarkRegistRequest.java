package com.finp.moic.userBookmark.adapter.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserBookmarkRegistRequest {

    @NotBlank
    private String shopName;

    @NotNull
    private String shopLocation;

    public UserBookmarkRegistRequest() {
    }

    @Builder
    public UserBookmarkRegistRequest(String shopName, String shopLocation) {
        this.shopName = shopName;
        this.shopLocation = shopLocation;
    }
}
