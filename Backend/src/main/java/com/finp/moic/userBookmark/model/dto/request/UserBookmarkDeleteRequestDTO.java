package com.finp.moic.userBookmark.model.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class UserBookmarkDeleteRequestDTO {

    private List<ShopRequestDTO> shopList;

    public UserBookmarkDeleteRequestDTO() {
    }

    @Builder
    public UserBookmarkDeleteRequestDTO(List<ShopRequestDTO> shopList) {
        this.shopList = shopList;
    }
}
