package com.finp.moic.card.model.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Getter
@ToString
public class CardResponseDTO implements Serializable {

    private String id;
    private String company;
    private String type;
    private String name;
    private String cardImage;
    private boolean mine;

    public CardResponseDTO() {
    }

    @QueryProjection
    public CardResponseDTO(UUID id, String company,
                           String type, String name, String cardImage) {
        this.id = id.toString();
        this.company = company;
        this.type = type;
        this.name = name;
        this.cardImage = cardImage;
        this.mine=false;
    }

    public void setFlag(boolean mine){
        this.mine=mine;
    }

}
