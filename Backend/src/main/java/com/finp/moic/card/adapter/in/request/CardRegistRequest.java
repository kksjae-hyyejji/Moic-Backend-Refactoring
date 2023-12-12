package com.finp.moic.card.adapter.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@ToString
public class CardRegistRequest {

    @NotNull
    @NotBlank
    private String cardName;

    public CardRegistRequest() {
    }

    @Builder
    public CardRegistRequest(@NotNull String cardName) {
        this.cardName = cardName;
    }
}
