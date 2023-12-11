package com.finp.moic.card.model.repository.queryDSL;

import com.finp.moic.card.model.dto.response.CardResponseDTO;
import com.finp.moic.card.model.entity.Card;

import java.util.List;

public interface CardRepositoryCustom {

    Boolean exist(String cardName);

    List<CardResponseDTO> search(String company, String type, String cardName);

    List<CardResponseDTO> findAllCard();
}
