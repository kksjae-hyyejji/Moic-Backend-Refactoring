package com.finp.moic.giftCard.application.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GiftcardDeleteServiceRequest {

    private String imageUrl;

    public GiftcardDeleteServiceRequest() {
    }

    @Builder
    public GiftcardDeleteServiceRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
