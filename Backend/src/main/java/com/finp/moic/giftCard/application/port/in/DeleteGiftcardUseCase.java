package com.finp.moic.giftCard.application.port.in;

import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;

public interface DeleteGiftcardUseCase {

    void delete(String id, String imageUrl);
}
