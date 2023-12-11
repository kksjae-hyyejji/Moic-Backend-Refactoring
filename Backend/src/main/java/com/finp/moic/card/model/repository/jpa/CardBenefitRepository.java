package com.finp.moic.card.model.repository.jpa;

import com.finp.moic.card.model.entity.CardBenefit;
import com.finp.moic.card.model.repository.queryDSL.CardBenefitRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBenefitRepository extends JpaRepository<CardBenefit,Long>, CardBenefitRepositoryCustom {
}
