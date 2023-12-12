package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.response.CardServiceResponse;

import java.util.List;

public interface CardQueryPort {

    Boolean exist(String cardName);

    List<CardServiceResponse> search(String company, String type, String cardName);

    List<CardServiceResponse> findAllCard();
}
