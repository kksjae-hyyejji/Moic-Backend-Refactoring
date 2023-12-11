package com.finp.moic.giftCard.model.repository.querydsl;

import com.finp.moic.giftCard.model.dto.response.GiftcardListResponseDTO;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;

import java.util.List;

public interface GiftcardRepositoryCustom {

    List<String> findAllShopNameByUserId(String userId);

    List<GiftcardListResponseDTO> findAllByUserId(String userId);

    List<GiftResponseDTO> findAllByUserIdAndShopName(String userId, String shopName);

}
