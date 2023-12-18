package com.finp.moic.shop.application;

import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.shop.application.port.in.SearchShopListUseCase;
import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.shop.application.port.out.RedisShopPersistencePort;
import com.finp.moic.shop.application.response.ShopSearchResponse;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.util.database.service.CacheRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchShopListService implements SearchShopListUseCase {

    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;
    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;
    private final RedisShopPersistencePort shopRedisPersistencePort;
    private final CacheRedisService cacheRedisService;

    @Override
    public List<ShopSearchResponse> searchShopListByKeyword(String keyword, double latitude, double longitude, String userId) {

        /**
         *
         * 아래 캐시 확인 로직 분리 가능
         *
         */
        /** Validation, Redis Access **/
        if(!cacheRedisService.existUserBenefitShopKey(userId)){
            List<String> benefitShopNameList= queryCardBenefitPersistencePort.findAllShopNameByUserId(userId);
            cacheRedisService.setUserBenefitShopList(benefitShopNameList,userId);
        }

        if(!cacheRedisService.existUserGiftShopKey(userId)){
            List<String> giftShopNameList= queryGiftcardPersistencePort.findAllShopNameByUserId(userId);
            cacheRedisService.setUserGiftShopList(giftShopNameList,userId);
        }
        /******************************************************************************************/

        /** RDB Access **/
        String shopName= queryShopPersistencePort.findShopNameByKeyword(keyword);

        /** Redis Access **/
        List<ShopSearchResponse> dto= shopRedisPersistencePort.searchShopListNearByUser(shopName,latitude,longitude);

        /**
         *
         * 아래 dto 세팅 로직 분리 가능
         *
         */
        /** DTO Builder **/
        for(int idx=0;idx<dto.size();idx++){
            if(cacheRedisService.existUserBenefitShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setBenefits(true);
            if(cacheRedisService.existUserGiftShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setGifts(true);
            if(queryUserBookmarkPersistencePort.exist(userId,dto.get(idx).getShopName(),dto.get(idx).getShopLocation())){
                dto.get(idx).setBookmark(true);
            }
        }
        /******************************************************************************************/

        return dto;
    }

    @Override
    public List<ShopSearchResponse> searchShopListByCategory(String category, double latitude, double longitude, String userId) {

        /**
         *
         * 아래 캐시 확인 로직 분리 가능
         *
         */
        /** Validation, Redis Access **/
        if(!cacheRedisService.existUserBenefitShopKey(userId)){
            List<String> benefitShopNameList= queryCardBenefitPersistencePort.findAllShopNameByUserId(userId);
            cacheRedisService.setUserBenefitShopList(benefitShopNameList,userId);
        }

        if(!cacheRedisService.existUserGiftShopKey(userId)){
            List<String> giftShopNameList= queryGiftcardPersistencePort.findAllShopNameByUserId(userId);
            cacheRedisService.setUserGiftShopList(giftShopNameList,userId);
        }
        /******************************************************************************************/

        /** RDB Access **/
        List<String> shopNameList= queryShopPersistencePort.findShopNameListByCategory(category);

        /** DTO Builder **/
        List<ShopSearchResponse> dto= new ArrayList<>();
        for(String shopName:shopNameList) {
            /** Redis Access **/
            dto.addAll(shopRedisPersistencePort.searchShopListNearByUser(shopName,latitude,longitude));
        }

        /**
         *
         * 아래 dto 세팅 로직 분리 가능
         *
         */
        /** DTO Builder **/
        for(int idx=0;idx<dto.size();idx++){
            if(cacheRedisService.existUserBenefitShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setBenefits(true);
            if(cacheRedisService.existUserGiftShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setGifts(true);
            if(queryUserBookmarkPersistencePort.exist(userId,dto.get(idx).getShopName(),dto.get(idx).getShopLocation())) {
                dto.get(idx).setBookmark(true);
            }
        }
        /******************************************************************************************/

        return dto;
    }
}
