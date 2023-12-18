package com.finp.moic.card.application;

import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.port.in.RegistCardUseCase;
import com.finp.moic.card.application.port.out.CommandUserCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryUserCardPersistencePort;
import com.finp.moic.card.domain.Card;
import com.finp.moic.card.domain.UserCard;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistCardService implements RegistCardUseCase {

    private final QueryCardPersistencePort queryCardPersistencePort;
    private final QueryUserPersistencePort queryUserPersistencePort;
    private final CommandUserCardPersistencePort commandUserCardPersistencePort;
    private final QueryUserCardPersistencePort queryUserCardPersistencePort;
    private final CacheRedisService cacheRedisService;

    @Override
    public void registCard(CardRegistRequest cardRegistRequest, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Card card= queryCardPersistencePort.findByName(cardRegistRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));
        User user= queryUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** Validation ***/
        /* 혜지 : 한 사용자에 대해 중복된 카드 등록 불가 */
        if(queryUserCardPersistencePort.exist(user.getId(),card.getName()))
            throw new AlreadyExistException(ExceptionEnum.CARD_REGIST_DUPLICATE);

        /*** RDB Access ***/
        /* 혜지 : userCardSeq 등의 기본 데이터셋 저장 */
        UserCard userCard= commandUserCardPersistencePort.save(UserCard.builder()
                .build());

        /*** Entity Builder ***/
        userCard=UserCard.builder()
                .userCardSeq(userCard.getUserCardSeq())
                .card(card)
                .user(user)
                .build();

        /*** RDB Access ***/
        /* 혜지 : userCard FK 저장 */
        commandUserCardPersistencePort.save(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);
    }
}
