package com.finp.moic.giftCard.adapter.in.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftcardDeleteRequestDTO {

    private String imageUrl;

    public GiftcardDeleteRequestDTO() {
    }

    @Builder
    public GiftcardDeleteRequestDTO(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
