package com.finp.moic.shop.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CategoryRequestDTO {

    private String mainCategory;
    private String subCategory;

    public CategoryRequestDTO() {
    }

    @Builder
    public CategoryRequestDTO(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
