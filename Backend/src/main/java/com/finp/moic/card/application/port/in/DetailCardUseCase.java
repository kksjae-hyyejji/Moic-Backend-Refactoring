package com.finp.moic.card.application.port.in;

import com.finp.moic.card.application.response.CardDetailServiceResponse;

public interface DetailCardUseCase {

    CardDetailServiceResponse detailCard(String cardName);
}
