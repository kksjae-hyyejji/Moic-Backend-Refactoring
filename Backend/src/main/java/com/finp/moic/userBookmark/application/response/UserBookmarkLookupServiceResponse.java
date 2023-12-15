package com.finp.moic.userBookmark.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserBookmarkLookupServiceResponse {

    private String category;
    private String shopName;
    private String shopLocation;
    private String address;
    private double latitude;
    private double longitude;

    public UserBookmarkLookupServiceResponse() {
    }

    @QueryProjection
    @Builder
    public UserBookmarkLookupServiceResponse(String category, String shopName, String shopLocation,
                                             String address, double latitude, double longitude) {
        this.category = category;
        this.shopName = shopName;
        this.shopLocation = shopLocation;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
