package com.finp.moic.card.application.port.out;

import com.finp.moic.card.adapter.out.persistence.CardQueryPort;
import com.finp.moic.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardPersistencePort extends JpaRepository<Card, UUID>, CardQueryPort {
    Optional<Card> findByName(String name);

    @Query(value = "SELECT DISTINCT company FROM card", nativeQuery = true)
    List<String> findAllCompany();

    @Query(value = "SELECT DISTINCT type FROM card", nativeQuery = true)
    List<String> findAllType();

}
