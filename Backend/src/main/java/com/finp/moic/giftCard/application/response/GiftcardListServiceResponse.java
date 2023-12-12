package com.finp.moic.giftCard.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class GiftcardListServiceResponse {

    String id;
    String imageUrl;
    LocalDate dueDate;

    @QueryProjection
    @Builder
    public GiftcardListServiceResponse(UUID id, String imageUrl, LocalDate dueDate) {
        this.id=id.toString();
        this.imageUrl = imageUrl;
        this.dueDate=dueDate;
    }
}
