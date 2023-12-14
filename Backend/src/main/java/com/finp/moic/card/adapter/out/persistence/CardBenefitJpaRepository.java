package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.domain.CardBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBenefitJpaRepository extends JpaRepository<CardBenefit,Long> {
}
