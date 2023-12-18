package com.finp.moic.card.application.port.in;

import com.finp.moic.card.adapter.in.request.CardRegistRequest;

public interface RegistCardUseCase {

    void registCard(CardRegistRequest cardRegistRequest, String userId);
}
