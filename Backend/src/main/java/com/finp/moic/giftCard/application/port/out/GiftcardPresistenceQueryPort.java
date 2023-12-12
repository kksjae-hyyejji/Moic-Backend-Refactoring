package com.finp.moic.giftCard.application.port.out;

import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;

import java.util.List;

public interface GiftcardPresistenceQueryPort {

    List<String> findAllShopNameByUserId(String userId);

    List<GiftcardListServiceResponse> findAllByUserId(String userId);

    List<GiftResponseDTO> findAllByUserIdAndShopName(String userId, String shopName);

}
