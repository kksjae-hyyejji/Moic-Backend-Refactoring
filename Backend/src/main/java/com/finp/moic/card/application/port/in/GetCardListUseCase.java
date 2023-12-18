package com.finp.moic.card.application.port.in;

import com.finp.moic.card.application.response.CardAllServiceReponse;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.CardServiceResponse;

import java.util.List;

public interface GetCardListUseCase {

    List<CardMineServiceResponse> getMyCardList(String userId);

    CardAllServiceReponse getCardList(String userId);

    List<CardServiceResponse> searchCard(String company, String type, String cardName, String userId);
}
