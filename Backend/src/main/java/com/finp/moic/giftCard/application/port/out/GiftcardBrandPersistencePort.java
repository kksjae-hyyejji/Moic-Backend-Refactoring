package com.finp.moic.giftCard.application.port.out;

import com.finp.moic.giftCard.domain.GiftcardBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftcardBrandPersistencePort extends JpaRepository<GiftcardBrand,Long>, GiftcardBrandPersistenceQueryPort {
}
