package com.finp.moic.card.application.port.out;

import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.domain.UserCard;

import java.util.List;
import java.util.Optional;

public interface QueryUserCardPersistencePort {
    boolean exist(String userId, String cardName);

    List<String> findAllCardNameByUserId(String userId);

    List<CardMineServiceResponse> findAllByUserId(String userId);

    Optional<UserCard> findByUserIdAndCardName(String userId, String cardName);
}
