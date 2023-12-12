package com.finp.moic.giftCard.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftcardBrandServiceResponse {

    private String mainCategory;
    private String category;

    public GiftcardBrandServiceResponse() {
    }

    @QueryProjection
    @Builder
    public GiftcardBrandServiceResponse(String mainCategory, String category) {
        this.mainCategory = mainCategory;
        this.category = category;
    }
}
