package com.finp.moic.shop.application;

import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.shop.adapter.in.request.ShopCategoryRequestInShopRecommandRequest;
import com.finp.moic.shop.adapter.in.request.ShopRecommandRequest;
import com.finp.moic.shop.application.port.in.RecommandShopListUseCase;
import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.shop.application.port.out.RedisShopPersistencePort;
import com.finp.moic.shop.application.response.ShopCardBenefitResponseInShopDetailResponse;
import com.finp.moic.shop.application.response.ShopGiftCardResponseInShopDetailResponse;
import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommandShopListService implements RecommandShopListUseCase {

    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;
    private final RedisShopPersistencePort shopRedisPersistencePort;

    @Override
    public List<ShopRecommandResponse> recommandShopList(ShopRecommandRequest shopRecommandRequest, String userId) {

        /** DTO Builder **/
        List<ShopRecommandResponse> dto=new ArrayList<>();

        for(ShopCategoryRequestInShopRecommandRequest shopCategoryRequestInShopRecommandRequest : shopRecommandRequest.getCategoryList()) {

            /** RDB Access **/
            List<String> shopNameList = queryShopPersistencePort.findShopNameListByMainCategoryAndSubCategory
                    (shopCategoryRequestInShopRecommandRequest.getMainCategory(), shopCategoryRequestInShopRecommandRequest.getSubCategory());

            /** Validation **/
            if(shopNameList==null){
                throw new NotFoundException(ExceptionEnum.SHOP_RECOMMAND_ERROR);
            }

            Collections.shuffle(shopNameList);
            String shopName=shopNameList.get(0);

            /** Redis Access **/
            ShopRecommandResponse redisDTO= shopRedisPersistencePort.findShopNearByUser//예외처리
                    (shopName, shopRecommandRequest.getLatitude(), shopRecommandRequest.getLongitude());
            /** Validation **/
            if(redisDTO==null){
                throw new NotFoundException(ExceptionEnum.SHOP_RECOMMAND_ERROR);
            }

            /** RDB Access **/
            List<ShopGiftCardResponseInShopDetailResponse> giftcardDTOList= queryGiftcardPersistencePort.findAllByUserIdAndShopName(userId,shopName);
            List<ShopCardBenefitResponseInShopDetailResponse> benefitDTOList= queryCardBenefitPersistencePort.findAllByUserIdAndShopName(userId,shopName);

            /** DTO Builder **/
            redisDTO.setBenefits(benefitDTOList);
            redisDTO.setGifts(giftcardDTOList);

            dto.add(redisDTO);
        }

        return dto;
    }
}
