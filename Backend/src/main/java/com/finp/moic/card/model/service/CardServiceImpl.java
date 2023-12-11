package com.finp.moic.card.model.service;


import com.finp.moic.card.model.dto.request.CardDeleteRequestDTO;
import com.finp.moic.card.model.dto.request.CardRegistRequestDTO;
import com.finp.moic.card.model.dto.response.*;
import com.finp.moic.card.model.entity.Card;
import com.finp.moic.card.model.entity.UserCard;
import com.finp.moic.card.model.repository.jpa.CardBenefitRepository;
import com.finp.moic.card.model.repository.jpa.CardRepository;
import com.finp.moic.card.model.repository.jpa.UserCardRepository;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final CardBenefitRepository cardBenefitRepository;
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;
    private final CacheRedisService cacheRedisService;
    private List<String> companyList;
    private List<String> typeList;
    @PostConstruct
    public void initalize(){
        companyList = cardRepository.findAllCompany();
        typeList = cardRepository.findAllType();
        Collections.sort(companyList);
        Collections.sort(typeList);
    }

    public CardServiceImpl(CardRepository cardRepository, CardBenefitRepository cardBenefitRepository,
                           UserRepository userRepository, UserCardRepository userCardRepository,
                           CacheRedisService cacheRedisService) {
        this.cardRepository = cardRepository;
        this.cardBenefitRepository = cardBenefitRepository;
        this.userRepository = userRepository;
        this.userCardRepository = userCardRepository;
        this.cacheRedisService = cacheRedisService;
    }

    @Override
    public void registCard(CardRegistRequestDTO cardRegistRequestDTO, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Card card=cardRepository.findByName(cardRegistRequestDTO.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** Validation ***/
        /* 혜지 : 한 사용자에 대해 중복된 카드 등록 불가 */
        if(userCardRepository.exist(user.getId(),card.getName()))
            throw new AlreadyExistException(ExceptionEnum.CARD_REGIST_DUPLICATE);

        /*** RDB Access ***/
        /* 혜지 : userCardSeq 등의 기본 데이터셋 저장 */
        UserCard userCard=userCardRepository.save(UserCard.builder()
                .build());

        /*** Entity Builder ***/
        userCard=UserCard.builder()
                .userCardSeq(userCard.getUserCardSeq())
                .card(card)
                .user(user)
                .build();

        /*** RDB Access ***/
        /* 혜지 : userCard FK 저장 */
        userCardRepository.save(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }

    @Override
    public CardAllReponseDTO getCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        List<CardResponseDTO> cardDTOList=cardRepository.findAllCard();
        List<String> userCardNameList=userCardRepository.findAllCardNameByUserId(userId);

        /*** DTO Builder ***/
        for(int idx=0;idx<cardDTOList.size();idx++){
            for(String userCard:userCardNameList){
                if(cardDTOList.get(idx).getName().equals(userCard)){
                    cardDTOList.get(idx).setFlag(true);
                    break;
                }
            }
        }
        CardAllReponseDTO dto=CardAllReponseDTO.builder()
                .companyList(companyList)
                .typeList(typeList)
                .cardList(cardDTOList)
                .build();

        return dto;
    }

    @Override
    public List<CardMineResponseDTO> getMyCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        /*** RDB Access ***/
        List<CardMineResponseDTO> dto=userCardRepository.findAllByUserId(userId);

        return dto;
    }

    @Override
    public void deleteCard(CardDeleteRequestDTO cardDeleteRequestDTO, String userId) {

        /*** Validation ***/
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));
        UserCard userCard=userCardRepository.findByUserIdAndCardName(userId,cardDeleteRequestDTO.getCardName())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_USER_NOT_FOUND));

        /*** RDB Access ***/
        userCardRepository.delete(userCard);

        /*** Redis Access ***/
        /* 혜지 : 값 업데이트가 되었으므로 캐싱 데이터 삭제 */
        cacheRedisService.removeUserBenefitShop(userId);

    }

    @Override
    public CardDetailResponseDTO detailCard(String cardName) {

        /*** Validation ***/
        Card card=cardRepository.findByName(cardName)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.CARD_NOT_FOUND));

        /*** RDB Access ***/
        /**
         * TO DO :: UserCardBenefit에 대한 캐싱 데이터 조회 및 백업
         **/
        List<CardBenefitResponseDTO> cardBenefitList=cardBenefitRepository.findByCardName(cardName);

        /*** DTO Builder ***/
        CardDetailResponseDTO dto=CardDetailResponseDTO.builder()
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
    public List<CardResponseDTO> searchCard(String company, String type, String cardName, String userId) {

        /*** Validation ***/
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** RDB Access ***/
        List<CardResponseDTO> dto=cardRepository.search(company,type,cardName);

        List<String> userCardNameList=userCardRepository.findAllCardNameByUserId(userId);

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
