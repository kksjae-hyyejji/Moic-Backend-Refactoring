package com.finp.moic.card.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CardRegistServiceRequest {

    @NotNull
    @NotBlank
    private String cardName;

    public CardRegistServiceRequest() {
    }

    @Builder
    public CardRegistServiceRequest(@NotNull String cardName) {
        this.cardName = cardName;
    }
}
