package com.finp.moic.card.model.service;

import com.finp.moic.card.model.dto.request.CardDeleteRequestDTO;
import com.finp.moic.card.model.dto.request.CardRegistRequestDTO;
import com.finp.moic.card.model.dto.response.*;

import java.util.List;

public interface CardService {

    void registCard(CardRegistRequestDTO cardRegistRequestDTO, String userId);

    List<CardMineResponseDTO> getMyCardList(String userId);

    CardAllReponseDTO getCardList(String userId);

    void deleteCard(CardDeleteRequestDTO cardDeleteRequestDTO, String userId);

    CardDetailResponseDTO detailCard(String cardName);

    List<CardResponseDTO> searchCard(String company, String type, String cardName, String userId);
}
