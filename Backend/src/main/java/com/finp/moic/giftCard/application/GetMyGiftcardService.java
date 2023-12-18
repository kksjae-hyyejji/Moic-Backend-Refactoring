package com.finp.moic.giftCard.application;

import com.finp.moic.giftCard.application.port.in.GetMyGiftcardUseCase;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMyGiftcardService implements GetMyGiftcardUseCase {

    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    @Override
    @Cacheable(value="giftcardList", key="#id")
    public List<GiftcardListServiceResponse> mygifts(String id) {

        return queryGiftcardPersistencePort.findAllByUserId(id);
    }
}
