package com.finp.moic.giftCard.application.port.out;


import com.finp.moic.giftCard.domain.Giftcard;

public interface CommandGiftcardPersistencePort {

    void delete(Giftcard giftcard);
    void save(Giftcard giftcard);

}
