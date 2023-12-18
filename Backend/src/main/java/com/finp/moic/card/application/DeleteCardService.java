package com.finp.moic.card.application;

import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.application.port.in.DeleteCardUseCase;
import com.finp.moic.card.application.port.out.CommandUserCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryUserCardPersistencePort;
import com.finp.moic.card.domain.UserCard;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCardService implements DeleteCardUseCase {

    private final QueryUserPersistencePort queryUserPersistencePort;
    private final CommandUserCardPersistencePort commandUserCardPersistencePort;
    private final QueryUserCardPersistencePort queryUserCardPersistencePort;
    private final CacheRedisService cacheRedisService;

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest, String userId) {

        /*** Validation ***/
        User user= queryUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));
        UserCard userCard= queryUserCardPersistencePort.findByUserIdAndCardName(userId, cardDeleteRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_USER_NOT_FOUND));

        /*** RDB Access ***/
        commandUserCardPersistencePort.delete(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }
}
