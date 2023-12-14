package com.finp.moic.card.application.port.out;

import com.finp.moic.card.domain.UserCard;

public interface CommandUserCardPersistencePort {
    UserCard save(UserCard userCard);

    void delete(UserCard userCard);
}
