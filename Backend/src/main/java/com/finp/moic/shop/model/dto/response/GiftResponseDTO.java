package com.finp.moic.shop.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
public class GiftResponseDTO {

    private String imageUrl;
    private String dueDate;

    public GiftResponseDTO() {
    }

    @QueryProjection
    @Builder

    public GiftResponseDTO(String imageUrl, LocalDate dueDate) {
        this.imageUrl = imageUrl;
        this.dueDate = dueDate.toString();
    }
}
