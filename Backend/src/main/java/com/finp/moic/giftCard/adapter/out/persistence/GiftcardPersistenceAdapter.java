package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.port.out.CommandGiftcardPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GiftcardPersistenceAdapter implements CommandGiftcardPersistencePort , QueryGiftcardPersistencePort {

    private final GiftcardJpaRepository giftcardJpaRepository;
    private final GiftcardQuerydslRepository querydslRepository;

    @Override
    public void delete(Giftcard giftcard) {
        giftcardJpaRepository.delete(giftcard);
    }

    @Override
    public void save(Giftcard giftcard) {
        giftcardJpaRepository.save(giftcard);
    }

    @Override
    public Optional<Giftcard> findByImageUrl(String url) {
        return giftcardJpaRepository.findByImageUrl(url);
    }

    @Override
    public List<GiftcardListServiceResponse> findAllByUserId(String id) {
        return querydslRepository.findAllByUserId(id);
    }

    @Override
    public List<String> findAllShopNameByUserId(String userId) {
        return querydslRepository.findAllShopNameByUserId(userId);
    }

    @Override
    public List<GiftResponseDTO> findAllByUserIdAndShopName(String userId, String shopName) {
        return querydslRepository.findAllByUserIdAndShopName(userId,shopName);
    }
}
