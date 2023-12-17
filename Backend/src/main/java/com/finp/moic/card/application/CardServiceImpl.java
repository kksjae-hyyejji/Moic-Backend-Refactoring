package com.finp.moic.card.application;


import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.port.out.CommandUserCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.card.application.port.out.QueryCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryUserCardPersistencePort;
import com.finp.moic.card.application.response.*;
import com.finp.moic.card.domain.Card;
import com.finp.moic.card.domain.UserCard;
import com.finp.moic.card.application.port.in.CardUseCase;
import com.finp.moic.user.domain.User;
import com.finp.moic.user.adpater.out.persistence.UserJpaRepository;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardUseCase {

    private final QueryCardPersistencePort queryCardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;
    private final UserJpaRepository commandUserPersistencePort;
    private final CommandUserCardPersistencePort commandUserCardPersistencePort;
    private final QueryUserCardPersistencePort queryUserCardPersistencePort;
    private final CacheRedisService cacheRedisService;

    private List<String> companyList;
    private List<String> typeList;
    @PostConstruct
    public void initalize(){
        companyList = queryCardPersistencePort.findAllCompany();
        typeList = queryCardPersistencePort.findAllType();
        Collections.sort(companyList);
        Collections.sort(typeList);
    }


    @Override
    public void registCard(CardRegistRequest cardRegistRequest, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Card card= queryCardPersistencePort.findByName(cardRegistRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));
        User user= commandUserPersistencePort.findById(userId)
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

    @Override
    public CardAllServiceReponse getCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        List<CardServiceResponse> cardDTOList= queryCardPersistencePort.findAllCard();
        List<String> userCardNameList= queryUserCardPersistencePort.findAllCardNameByUserId(userId);

        /*** DTO Builder ***/
        for(int idx=0;idx<cardDTOList.size();idx++){
            for(String userCard:userCardNameList){
                if(cardDTOList.get(idx).getName().equals(userCard)){
                    cardDTOList.get(idx).setFlag(true);
                    break;
                }
            }
        }
        CardAllServiceReponse dto= CardAllServiceReponse.builder()
                .companyList(companyList)
                .typeList(typeList)
                .cardList(cardDTOList)
                .build();

        return dto;
    }

    @Override
    public List<CardMineServiceResponse> getMyCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        /*** RDB Access ***/
        List<CardMineServiceResponse> dto= queryUserCardPersistencePort.findAllByUserId(userId);

        return dto;
    }

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest, String userId) {

        /*** Validation ***/
        User user= commandUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));
        UserCard userCard= queryUserCardPersistencePort.findByUserIdAndCardName(userId, cardDeleteRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_USER_NOT_FOUND));

        /*** RDB Access ***/
        commandUserCardPersistencePort.delete(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }

    @Override
    public CardDetailServiceResponse detailCard(String cardName) {

        /*** Validation ***/
        Card card= queryCardPersistencePort.findByName(cardName)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));

        /*** RDB Access ***/
        /**
         * TO DO :: UserCardBenefit에 대한 캐싱 데이터 조회 및 백업
         **/
        List<CardBenefitServiceResponse> cardBenefitList= queryCardBenefitPersistencePort.findByCardName(cardName);

        /*** DTO Builder ***/
        CardDetailServiceResponse dto= CardDetailServiceResponse.builder()
                .id(card.getCardSeq())
                .company(card.getCompany())
                .type(card.getType())
                .name(card.getName())
                .cardImage(card.getCardImage())
                .cardBenefit(cardBenefitList)
                .build();

        return dto;
    }

    @Override
    public List<CardServiceResponse> searchCard(String company, String type, String cardName, String userId) {

        /*** Validation ***/
        User user= commandUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** RDB Access ***/
        List<CardServiceResponse> dto= queryCardPersistencePort.search(company,type,cardName);

        List<String> userCardNameList= queryUserCardPersistencePort.findAllCardNameByUserId(userId);

        /*** DTO Builder ***/
        for(int idx=0;idx<dto.size();idx++){
            for(String userCard:userCardNameList){
                if(dto.get(idx).getName().equals(userCard)){
                    dto.get(idx).setFlag(true);
                    break;
                }
            }
        }

        return dto;
    }
}
