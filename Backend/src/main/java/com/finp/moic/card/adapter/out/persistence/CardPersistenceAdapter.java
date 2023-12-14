package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.port.out.QueryCardPersistencePort;
import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.card.domain.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CardPersistenceAdapter implements QueryCardPersistencePort {

    private final CardJpaRepository jpaRepository;
    private final CardQuerydslRepository querydslRepository;

    @Override
    public boolean exist(String cardName) {
        return querydslRepository.exist(cardName);
    }

    @Override
    public List<CardServiceResponse> search(String company, String type, String cardName) {
        return querydslRepository.search(company,type,cardName);
    }

    @Override
    public List<CardServiceResponse> findAllCard() {
        return querydslRepository.findAllCard();
    }

    @Override
    public Optional<Card> findByName(String name) {
        return jpaRepository.findByName(name);
    }

    @Override
    public List<String> findAllCompany() {
        return jpaRepository.findAllCompany();
    }

    @Override
    public List<String> findAllType() {
        return jpaRepository.findAllType();
    }
}
