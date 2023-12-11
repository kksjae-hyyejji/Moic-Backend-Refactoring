package com.finp.moic.card.model.dto.response;

import com.finp.moic.card.model.entity.CardBenefit;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
public class CardDetailResponseDTO implements Serializable {

    private String id;
    private String company;
    private String type;
    private String name;
    private String cardImage;
    private List<CardBenefitResponseDTO> cardBenefit;

    public CardDetailResponseDTO() {
    }

    @Builder
    public CardDetailResponseDTO(UUID id, String company, String type,
                                 String name, String cardImage, List<CardBenefitResponseDTO> cardBenefit) {
        this.id = id.toString();
        this.company = company;
        this.type = type;
        this.name = name;
        this.cardImage = cardImage;
        this.cardBenefit = cardBenefit;
    }
}
