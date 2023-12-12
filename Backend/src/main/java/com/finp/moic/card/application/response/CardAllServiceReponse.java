package com.finp.moic.card.application.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
public class CardAllServiceReponse implements Serializable {

    private List<String> companyList;
    private List<String> typeList;
    private List<CardServiceResponse> cardList;

    public CardAllServiceReponse() {
    }

    @QueryProjection
    @Builder
    public CardAllServiceReponse(List<String> companyList, List<String> typeList,
                                 List<CardServiceResponse> cardList) {
        this.companyList = companyList;
        this.typeList = typeList;
        this.cardList = cardList;
    }
}
