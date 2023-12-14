package com.finp.moic.giftCard.application.port.out;

import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;

import java.util.List;
import java.util.Optional;

public interface QueryGiftcardPersistencePort {
    Optional<Giftcard> findByImageUrl(String url);
    List<GiftcardListServiceResponse> findAllByUserId(String id);

    List<String> findAllShopNameByUserId(String userId);

    List <GiftResponseDTO> findAllByUserIdAndShopName(String userId, String shopName);
}
