package com.finp.moic.card.application;

import com.finp.moic.card.application.port.in.GetCardListUseCase;
import com.finp.moic.card.application.port.out.QueryCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryUserCardPersistencePort;
import com.finp.moic.card.application.response.CardAllServiceReponse;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.application.response.CardServiceResponse;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCardListService implements GetCardListUseCase {

    private final QueryCardPersistencePort queryCardPersistencePort;
    private final QueryUserPersistencePort queryUserPersistencePort;
    private final QueryUserCardPersistencePort queryUserCardPersistencePort;

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
    public List<CardMineServiceResponse> getMyCardList(String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 삭제된 데이터 가져오지 않기
         * */

        /*** RDB Access ***/
        List<CardMineServiceResponse> dto= queryUserCardPersistencePort.findAllByUserId(userId);

        return dto;
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
    public List<CardServiceResponse> searchCard(String company, String type, String cardName, String userId) {

        /*** Validation ***/
        User user= queryUserPersistencePort.findById(userId)
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
