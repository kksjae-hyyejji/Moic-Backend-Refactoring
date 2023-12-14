package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.domain.GiftcardBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftcardBrandJpaRepository extends JpaRepository<GiftcardBrand,Long> {

}
