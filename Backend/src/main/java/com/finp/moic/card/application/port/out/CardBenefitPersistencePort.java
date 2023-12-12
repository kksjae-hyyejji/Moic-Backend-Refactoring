package com.finp.moic.card.application.port.out;

import com.finp.moic.card.adapter.out.persistence.CardBenefitQueryPort;
import com.finp.moic.card.domain.CardBenefit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardBenefitPersistencePort extends JpaRepository<CardBenefit,Long>, CardBenefitQueryPort {
}
