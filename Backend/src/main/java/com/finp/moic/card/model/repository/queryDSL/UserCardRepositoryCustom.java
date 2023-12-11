package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardMineResponseDTO;
import com.finp.moic.card.model.entity.Card;

import java.util.List;

public interface UserCardRepositoryCustom {

    Boolean exist(String userId, String cardName);

    List<String> findAllCardNameByUserId(String userId);

    List<CardMineResponseDTO> findAllByUserId(String userId);
}
