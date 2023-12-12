package com.finp.moic.giftCard.application.port.out;

import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;

public interface GiftcardBrandPersistenceQueryPort {
    GiftcardBrandServiceResponse findByName(String shopName);
}
