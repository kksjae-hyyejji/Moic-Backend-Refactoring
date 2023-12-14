package com.finp.moic.card.application.port.out;

import com.finp.moic.card.application.response.CardBenefitServiceResponse;
import com.finp.moic.shop.model.dto.response.BenefitResponseDTO;

import java.util.List;

public interface QueryCardBenefitPersistencePort {
    boolean exist(String cardName);

    List<CardBenefitServiceResponse> findByCardName(String cardName);

    List<BenefitResponseDTO> findAllByUserIdAndShopName(String userId, String shopName);

    List<String> findAllShopNameByUserId(String userId);
}
