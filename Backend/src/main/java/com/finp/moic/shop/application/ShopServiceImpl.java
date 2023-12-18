package com.finp.moic.shop.application;

import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;

import com.finp.moic.shop.adapter.in.request.ShopCategoryRequestInShopRecommandRequest;
import com.finp.moic.shop.adapter.in.request.ShopDetailRequest;
import com.finp.moic.shop.adapter.in.request.ShopRecommandRequest;
import com.finp.moic.shop.application.port.in.ShopUseCase;
import com.finp.moic.shop.application.port.out.RedisShopPersistencePort;
import com.finp.moic.shop.application.response.*;
import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopUseCase {

    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;
    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;
    private final RedisShopPersistencePort shopRedisPersistencePort;
    private final CacheRedisService cacheRedisService;


    @Override
    public ShopDetailResponse detailShop(ShopDetailRequest request, String userId) {

        /** Validation, RDB Access **/
        ShopDetailResponse dto= queryShopPersistencePort
                .findShopDetailDTOByNameAndLocation(request.getShopName(), request.getShopLocation())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));

        /** 가맹점 조회 시 북마크가 등록되어 있을 경우 체크한다 **/
        if(queryUserBookmarkPersistencePort.exist(userId, request.getShopName(), request.getShopLocation())){
            dto.setBookmark(true);
        }

        List<ShopGiftCardResponseInShopDetailResponse> giftcardDTOList= queryGiftcardPersistencePort.findAllByUserIdAndShopName(userId, request.getShopName());
        List<ShopCardBenefitResponseInShopDetailResponse> benefitDTOList= queryCardBenefitPersistencePort.findAllByUserIdAndShopName(userId, request.getShopName());

        /** DTO Builder **/
        dto.setBenefits(benefitDTOList);
        dto.setGifts(giftcardDTOList);

        return dto;
    }

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
