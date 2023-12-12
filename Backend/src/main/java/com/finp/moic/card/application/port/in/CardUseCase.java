package com.finp.moic.card.application.port.in;

import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.response.CardAllServiceReponse;
import com.finp.moic.card.application.response.CardDetailServiceResponse;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.CardServiceResponse;

import java.util.List;

public interface CardUseCase {

    void registCard(CardRegistRequest cardRegistRequest, String userId);

    List<CardMineServiceResponse> getMyCardList(String userId);

    CardAllServiceReponse getCardList(String userId);

    void deleteCard(CardDeleteRequest cardDeleteRequest, String userId);

    CardDetailServiceResponse detailCard(String cardName);

    List<CardServiceResponse> searchCard(String company, String type, String cardName, String userId);
}
