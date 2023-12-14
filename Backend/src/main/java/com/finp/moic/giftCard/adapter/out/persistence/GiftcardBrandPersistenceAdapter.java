package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.port.out.CommandGiftcardBrandPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardBrandPersistencePort;
import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GiftcardBrandPersistenceAdapter implements CommandGiftcardBrandPersistencePort, QueryGiftcardBrandPersistencePort {

    private final GiftcardBrandJpaRepository giftcardBrandJpaRepository;
    private final GiftcardBrandQuerydslRepository giftcardBrandQuerydslRepository;

    @Override
    public GiftcardBrandServiceResponse findByName(String shopName) {
        return giftcardBrandQuerydslRepository.findByName(shopName);
    }
}
