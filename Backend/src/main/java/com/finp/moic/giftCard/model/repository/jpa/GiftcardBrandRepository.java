package com.finp.moic.giftCard.model.repository.jpa;

import com.finp.moic.giftCard.model.entity.GiftcardBrand;
import com.finp.moic.giftCard.model.repository.querydsl.GiftcardBrandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftcardBrandRepository extends JpaRepository<GiftcardBrand,Long>, GiftcardBrandRepositoryCustom {
}
