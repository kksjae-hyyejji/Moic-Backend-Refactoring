package com.finp.moic.giftCard.model.repository.querydsl;

import com.finp.moic.giftCard.model.dto.response.GiftcardBrandResponseDTO;

public interface GiftcardBrandRepositoryCustom {
    GiftcardBrandResponseDTO findByName(String shopName);
}
