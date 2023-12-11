package com.finp.moic.giftCard.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftcardBrandResponseDTO {

    private String mainCategory;
    private String category;

    public GiftcardBrandResponseDTO() {
    }

    @QueryProjection
    @Builder
    public GiftcardBrandResponseDTO(String mainCategory, String category) {
        this.mainCategory = mainCategory;
        this.category = category;
    }
}
