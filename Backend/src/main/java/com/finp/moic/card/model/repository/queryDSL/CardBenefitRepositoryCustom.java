package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardBenefitResponseDTO;
import com.finp.moic.shop.model.dto.response.BenefitResponseDTO;

import java.util.List;

public interface CardBenefitRepositoryCustom {

    Boolean exist(String cardName);

    List<CardBenefitResponseDTO> findByCardName(String cardName);

    List<BenefitResponseDTO> findAllByUserIdAndShopName(String userId, String shopName);

    List<String> findAllShopNameByUserId(String userId);

}
