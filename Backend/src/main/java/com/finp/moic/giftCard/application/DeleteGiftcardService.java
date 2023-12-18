package com.finp.moic.giftCard.application;

import com.finp.moic.giftCard.application.port.in.DeleteGiftcardUseCase;
import com.finp.moic.giftCard.application.port.out.CommandGiftcardPersistencePort;
import com.finp.moic.giftCard.application.port.out.CommandImageS3Port;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.giftCard.domain.Giftcard;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteGiftcardService implements DeleteGiftcardUseCase {

    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final CommandImageS3Port commandImageS3Port;
    private final CommandGiftcardPersistencePort commandGiftcardPersistencePort;
    @Override
    @CacheEvict(value="giftcardList", key="#id")
    public void delete(String id, String imageUrl) {

        Giftcard giftcard = queryGiftcardPersistencePort.findByImageUrl(imageUrl)
                .orElseThrow(() -> new NotFoundException(ExceptionEnum.GIFTCARD_NOT_FOUND));
        commandImageS3Port.deleteGiftcard(imageUrl);
        commandGiftcardPersistencePort.delete(giftcard);

    }
}
