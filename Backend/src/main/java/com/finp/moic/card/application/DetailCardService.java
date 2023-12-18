package com.finp.moic.card.application;

import com.finp.moic.card.application.port.in.DetailCardUseCase;
import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.card.application.port.out.QueryCardPersistencePort;
import com.finp.moic.card.application.response.CardBenefitServiceResponse;
import com.finp.moic.card.application.response.CardDetailServiceResponse;
import com.finp.moic.card.domain.Card;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailCardService implements DetailCardUseCase {

    private final QueryCardPersistencePort queryCardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;

    @Override
    public CardDetailServiceResponse detailCard(String cardName) {

        /*** Validation ***/
        Card card= queryCardPersistencePort.findByName(cardName)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));

        /*** RDB Access ***/
        /**
         * TO DO :: UserCardBenefit에 대한 캐싱 데이터 조회 및 백업
         **/
        List<CardBenefitServiceResponse> cardBenefitList= queryCardBenefitPersistencePort.findByCardName(cardName);

        /*** DTO Builder ***/
        CardDetailServiceResponse dto= CardDetailServiceResponse.builder()
                .id(card.getCardSeq())
                .company(card.getCompany())
                .type(card.getType())
                .name(card.getName())
                .cardImage(card.getCardImage())
                .cardBenefit(cardBenefitList)
                .build();

        return dto;
    }
}
