package com.finp.moic.giftCard.model.repository.jpa;

import com.finp.moic.giftCard.model.entity.Giftcard;
import com.finp.moic.giftCard.model.repository.querydsl.GiftcardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftcardRepository extends JpaRepository<Giftcard,Long>, GiftcardRepositoryCustom {

    Optional<Giftcard> findByImageUrl(String url);
}
