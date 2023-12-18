package com.finp.moic.giftCard.application.port.in;

import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;

import java.util.List;

public interface GetMyGiftcardUseCase {

    List<GiftcardListServiceResponse> mygifts(String id);
}
