package com.finp.moic.card.application.port.out;

import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.card.domain.Card;

import java.util.List;
import java.util.Optional;

public interface QueryCardPersistencePort {

    boolean exist(String cardName);

    List<CardServiceResponse> search(String company, String type, String cardName);

    List<CardServiceResponse> findAllCard();

    Optional<Card> findByName(String name);

    List<String> findAllCompany();

    List<String> findAllType();
}
