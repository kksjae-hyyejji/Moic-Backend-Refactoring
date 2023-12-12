package com.finp.moic.card.application.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class CardDetailServiceResponse implements Serializable {

    private String id;
    private String company;
    private String type;
    private String name;
    private String cardImage;
    private List<CardBenefitServiceResponse> cardBenefit;

    public CardDetailServiceResponse() {
    }

    @Builder
    public CardDetailServiceResponse(UUID id, String company, String type,
                                     String name, String cardImage, List<CardBenefitServiceResponse> cardBenefit) {
        this.id = id.toString();
        this.company = company;
        this.type = type;
        this.name = name;
        this.cardImage = cardImage;
        this.cardBenefit = cardBenefit;
    }
}
