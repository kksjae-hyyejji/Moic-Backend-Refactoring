package com.finp.moic.giftCard.application.port.out;

import com.finp.moic.giftCard.domain.Giftcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftcardPersistencePort extends JpaRepository<Giftcard,Long>, GiftcardPresistenceQueryPort {

    Optional<Giftcard> findByImageUrl(String url);
}
