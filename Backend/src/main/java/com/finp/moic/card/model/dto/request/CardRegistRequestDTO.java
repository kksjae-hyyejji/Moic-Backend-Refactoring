package com.finp.moic.card.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
public class CardRegistRequestDTO {

    @NotNull
    @NotBlank
    private String cardName;

    public CardRegistRequestDTO() {
    }

    @Builder
    public CardRegistRequestDTO(@NotNull String cardName) {
        this.cardName = cardName;
    }
}
