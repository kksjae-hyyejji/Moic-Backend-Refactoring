package com.finp.moic.card.adapter.in.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CardDeleteRequest {

    @NotNull
    @NotBlank
    private String cardName;

    public CardDeleteRequest() {
    }

    @Builder
    public CardDeleteRequest(@NotNull String cardName) {
        this.cardName = cardName;
    }

}
