package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.domain.Giftcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftcardJpaRepository extends JpaRepository<Giftcard, Long> {
    Optional<Giftcard> findByImageUrl(String url);
}
