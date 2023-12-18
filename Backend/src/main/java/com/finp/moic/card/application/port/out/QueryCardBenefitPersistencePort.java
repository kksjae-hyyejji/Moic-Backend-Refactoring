package com.finp.moic.card.application.port.out;

import com.finp.moic.card.application.response.CardBenefitServiceResponse;
import com.finp.moic.shop.application.response.ShopCardBenefitResponseInShopDetailResponse;

import java.util.List;

public interface QueryCardBenefitPersistencePort {
    boolean exist(String cardName);

    List<CardBenefitServiceResponse> findByCardName(String cardName);

    List<ShopCardBenefitResponseInShopDetailResponse> findAllByUserIdAndShopName(String userId, String shopName);

    List<String> findAllShopNameByUserId(String userId);
}
