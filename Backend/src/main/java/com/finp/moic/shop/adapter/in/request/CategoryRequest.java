package com.finp.moic.shop.adapter.in.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryRequest {

    private String mainCategory;
    private String subCategory;

    public CategoryRequest() {
    }

    @Builder
    public CategoryRequest(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
