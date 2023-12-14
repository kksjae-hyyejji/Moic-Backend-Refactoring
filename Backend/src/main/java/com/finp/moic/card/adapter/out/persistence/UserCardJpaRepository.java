package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.domain.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCardJpaRepository extends JpaRepository<UserCard,Long> {

    Optional<UserCard> findByUserIdAndCardName(String userId, String cardName);
}
