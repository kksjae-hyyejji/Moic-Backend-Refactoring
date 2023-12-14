package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.card.application.response.CardBenefitServiceResponse;
import com.finp.moic.shop.application.response.BenefitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CardBenefitPersistenceAdapter implements QueryCardBenefitPersistencePort {

    private final CardBenefitJpaRepository jpaRepository;
    private final CardBenefitQuerydslRepository querydslRepository;

    @Override
    public boolean exist(String cardName) {
        return querydslRepository.exist(cardName);
    }

    @Override
    public List<CardBenefitServiceResponse> findByCardName(String cardName) {
        return querydslRepository.findByCardName(cardName);
    }

    @Override
    public List<BenefitResponse> findAllByUserIdAndShopName(String userId, String shopName) {
        return findAllByUserIdAndShopName(userId,shopName);
    }

    @Override
    public List<String> findAllShopNameByUserId(String userId) {
        return querydslRepository.findAllShopNameByUserId(userId);
    }
}
