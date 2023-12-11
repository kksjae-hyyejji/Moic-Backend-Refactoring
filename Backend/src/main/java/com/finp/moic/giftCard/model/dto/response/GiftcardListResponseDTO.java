package com.finp.moic.giftCard.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class GiftcardListResponseDTO {

    String id;
    String imageUrl;
    LocalDate dueDate;

    @QueryProjection
    @Builder
    public GiftcardListResponseDTO(UUID id, String imageUrl, LocalDate dueDate) {
        this.id=id.toString();
        this.imageUrl = imageUrl;
        this.dueDate=dueDate;
    }
}
