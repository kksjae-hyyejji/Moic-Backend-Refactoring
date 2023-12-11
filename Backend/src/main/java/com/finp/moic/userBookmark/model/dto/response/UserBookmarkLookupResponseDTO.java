package com.finp.moic.userBookmark.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserBookmarkLookupResponseDTO {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private double latitude;
    private double longitude;

    public UserBookmarkLookupResponseDTO() {
    }

    @QueryProjection
    @Builder
    public UserBookmarkLookupResponseDTO(String category, String shopName, String shopLocation,
                           String address, double latitude, double longitude) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
