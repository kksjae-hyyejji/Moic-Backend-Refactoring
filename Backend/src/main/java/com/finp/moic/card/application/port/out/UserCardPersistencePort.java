package com.finp.moic.card.application.port.out;

import com.finp.moic.card.adapter.out.persistence.UserCardQueryPort;
import com.finp.moic.card.domain.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCardPersistencePort extends JpaRepository<UserCard,Long>, UserCardQueryPort {

    Optional<UserCard> findByUserIdAndCardName(String userId,String cardName);

}
