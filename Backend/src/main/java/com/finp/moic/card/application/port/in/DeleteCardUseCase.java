package com.finp.moic.card.application.port.in;

import com.finp.moic.card.adapter.in.request.CardDeleteRequest;

public interface DeleteCardUseCase {

    void deleteCard(CardDeleteRequest cardDeleteRequest, String userId);

}
