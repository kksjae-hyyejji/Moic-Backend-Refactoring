package com.finp.moic.card.application;


import com.finp.moic.card.adapter.in.request.CardDeleteRequest;
import com.finp.moic.card.adapter.in.request.CardRegistRequest;
import com.finp.moic.card.application.response.*;
import com.finp.moic.card.domain.Card;
import com.finp.moic.card.domain.UserCard;
import com.finp.moic.card.application.port.out.CardBenefitPersistencePort;
import com.finp.moic.card.application.port.out.CardPersistencePort;
import com.finp.moic.card.application.port.out.UserCardPersistencePort;
import com.finp.moic.card.application.port.in.CardUseCase;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardServiceImpl implements CardUseCase {

    private final CardPersistencePort cardPersistencePort;
    private final CardBenefitPersistencePort cardBenefitPersistencePort;
    private final UserRepository userRepository;
    private final UserCardPersistencePort userCardPersistencePort;
    private final CacheRedisService cacheRedisService;
    private List<String> companyList;
    private List<String> typeList;
    @PostConstruct
    public void initalize(){
        companyList = cardPersistencePort.findAllCompany();
        typeList = cardPersistencePort.findAllType();
        Collections.sort(companyList);
        Collections.sort(typeList);
    }

    @Autowired
    public CardServiceImpl(CardPersistencePort cardPersistencePort, CardBenefitPersistencePort cardBenefitPersistencePort,
                           UserRepository userRepository, UserCardPersistencePort userCardPersistencePort,
                           CacheRedisService cacheRedisService) {
        this.cardPersistencePort = cardPersistencePort;
        this.cardBenefitPersistencePort = cardBenefitPersistencePort;
        this.userRepository = userRepository;
        this.userCardPersistencePort = userCardPersistencePort;
        this.cacheRedisService = cacheRedisService;
    }

    @Override
    public void registCard(CardRegistRequest cardRegistRequest, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Card card= cardPersistencePort.findByName(cardRegistRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** Validation ***/
        /* 혜지 : 한 사용자에 대해 중복된 카드 등록 불가 */
        if(userCardPersistencePort.exist(user.getId(),card.getName()))
            throw new AlreadyExistException(ExceptionEnum.CARD_REGIST_DUPLICATE);

        /*** RDB Access ***/
        /* 혜지 : userCardSeq 등의 기본 데이터셋 저장 */
        UserCard userCard= userCardPersistencePort.save(UserCard.builder()
                .build());

        /*** Entity Builder ***/
        userCard=UserCard.builder()
                .userCardSeq(userCard.getUserCardSeq())
                .card(card)
                .user(user)
                .build();

        /*** RDB Access ***/
        /* 혜지 : userCard FK 저장 */
        userCardPersistencePort.save(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }

    @Override
    public CardAllServiceReponse getCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        List<CardServiceResponse> cardDTOList= cardPersistencePort.findAllCard();
        List<String> userCardNameList= userCardPersistencePort.findAllCardNameByUserId(userId);

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
        List<CardMineServiceResponse> dto= userCardPersistencePort.findAllByUserId(userId);

        return dto;
    }

    @Override
    public void deleteCard(CardDeleteRequest cardDeleteRequest, String userId) {

        /*** Validation ***/
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));
        UserCard userCard= userCardPersistencePort.findByUserIdAndCardName(userId, cardDeleteRequest.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_USER_NOT_FOUND));

        /*** RDB Access ***/
        userCardPersistencePort.delete(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }

    @Override
    public CardDetailServiceResponse detailCard(String cardName) {

        /*** Validation ***/
        Card card= cardPersistencePort.findByName(cardName)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));

        /*** RDB Access ***/
        /**
         * TO DO :: UserCardBenefit에 대한 캐싱 데이터 조회 및 백업
         **/
        List<CardBenefitServiceResponse> cardBenefitList= cardBenefitPersistencePort.findByCardName(cardName);

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
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** RDB Access ***/
        List<CardServiceResponse> dto= cardPersistencePort.search(company,type,cardName);

        List<String> userCardNameList= userCardPersistencePort.findAllCardNameByUserId(userId);

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
