package com.finp.moic.shop.model.service;

import com.finp.moic.card.model.repository.jpa.CardBenefitRepository;
import com.finp.moic.giftCard.model.repository.jpa.GiftcardRepository;
import com.finp.moic.shop.model.dto.request.CategoryRequestDTO;
import com.finp.moic.shop.model.dto.request.ShopRecommandRequestDTO;
import com.finp.moic.shop.model.dto.response.*;
import com.finp.moic.shop.model.repository.ShopRepository;
import com.finp.moic.userBookmark.model.repository.UserBookmarkRepository;
import com.finp.moic.util.database.service.CacheRedisService;
import com.finp.moic.util.database.service.ShopLocationRedisService;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{

    private final ShopRepository shopRepository;
    private final CardBenefitRepository cardBenefitRepository;
    private final GiftcardRepository giftcardRepository;
    private final UserBookmarkRepository userBookmarkRepository;
    private final ShopLocationRedisService shopLocationRedisService;
    private final CacheRedisService cacheRedisService;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, CardBenefitRepository cardBenefitRepository,
                           GiftcardRepository giftcardRepository, UserBookmarkRepository userBookmarkRepository,
                           ShopLocationRedisService shopLocationRedisService, CacheRedisService cacheRedisService) {
        this.shopRepository = shopRepository;
        this.cardBenefitRepository = cardBenefitRepository;
        this.giftcardRepository = giftcardRepository;
        this.userBookmarkRepository = userBookmarkRepository;
        this.shopLocationRedisService = shopLocationRedisService;
        this.cacheRedisService = cacheRedisService;
    }

    @Override
    public ShopDetailResponseDTO detailShop(String shopName, String shopLocation, String userId) {

        /** Validation, RDB Access **/
        ShopDetailResponseDTO dto=shopRepository
                .findByNameAndLocation(shopName,shopLocation)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));

        if(userBookmarkRepository.exist(userId,shopName,shopLocation)){
            dto.setBookmark(true);
        }

        List<BenefitResponseDTO> benefitDTOList=cardBenefitRepository.findAllByUserIdAndShopName(userId,shopName);
        List<GiftResponseDTO> giftcardDTOList=giftcardRepository.findAllByUserIdAndShopName(userId,shopName);

        /** DTO Builder **/
        dto.setBenefits(benefitDTOList);
        dto.setGifts(giftcardDTOList);

        return dto;
    }

    @Override
    public List<ShopSearchResponseDTO> searchShop(String keyword, double latitude, double longitude, String userId) {

        /** Validation, Redis Access **/
        if(!cacheRedisService.existUserBenefitShopKey(userId)){
            List<String> benefitShopNameList=cardBenefitRepository.findAllShopNameByUserId(userId);
            cacheRedisService.setUserBenefitShopList(benefitShopNameList,userId);
        }

        if(!cacheRedisService.existUserGiftShopKey(userId)){
            List<String> giftShopNameList=giftcardRepository.findAllShopNameByUserId(userId);
            cacheRedisService.setUserGiftShopList(giftShopNameList,userId);
        }

        /** RDB Access **/
        String shopName=shopRepository.findShopNameByKeyword(keyword);

        /** Redis Access **/
        List<ShopSearchResponseDTO> dto=shopLocationRedisService.searchShopListNearByUser(shopName,latitude,longitude);

        /** DTO Builder **/
        for(int idx=0;idx<dto.size();idx++){
            if(cacheRedisService.existUserBenefitShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setBenefits(true);
            if(cacheRedisService.existUserGiftShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setGifts(true);
            if(userBookmarkRepository.exist(userId,dto.get(idx).getShopName(),dto.get(idx).getShopLocation())){
                dto.get(idx).setBookmark(true);
            }
        }

        return dto;
    }

    @Override
    public List<ShopSearchResponseDTO> getShopListByCategory(String category, double latitude, double longitude, String userId) {

        /** Validation, Redis Access **/
        if(!cacheRedisService.existUserBenefitShopKey(userId)){
            List<String> benefitShopNameList=cardBenefitRepository.findAllShopNameByUserId(userId);
            cacheRedisService.setUserBenefitShopList(benefitShopNameList,userId);
        }

        if(!cacheRedisService.existUserGiftShopKey(userId)){
            List<String> giftShopNameList=giftcardRepository.findAllShopNameByUserId(userId);
            cacheRedisService.setUserGiftShopList(giftShopNameList,userId);
        }

        /** RDB Access **/
        List<String> shopNameList=shopRepository.findAllShopNameByCategory(category);

        /** DTO Builder **/
        List<ShopSearchResponseDTO> dto=new ArrayList<>();
        for(String shopName:shopNameList) {
            /** Redis Access **/
            dto.addAll(shopLocationRedisService.searchShopListNearByUser(shopName,latitude,longitude));
        }

        /** DTO Builder **/
        for(int idx=0;idx<dto.size();idx++){
            if(cacheRedisService.existUserBenefitShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setBenefits(true);
            if(cacheRedisService.existUserGiftShop(dto.get(idx).getShopName(),userId))
                dto.get(idx).setGifts(true);
            if(userBookmarkRepository.exist(userId,dto.get(idx).getShopName(),dto.get(idx).getShopLocation())) {
                dto.get(idx).setBookmark(true);
            }
        }

        return dto;
    }

    @Override
    public List<ShopRecommandResponseDTO> recommandShopList(ShopRecommandRequestDTO shopRecommandRequestDTO, String userId) {

        /** DTO Builder **/
        List<ShopRecommandResponseDTO> dto=new ArrayList<>();

        for(CategoryRequestDTO categoryRequestDTO:shopRecommandRequestDTO.getCategoryList()) {

            /** RDB Access **/
            List<String> shopNameList = shopRepository.findAllShopNameByMainCategoryAndSubCategory
                    (categoryRequestDTO.getMainCategory(),categoryRequestDTO.getSubCategory());

            /** Validation **/
            if(shopNameList==null){
                throw new NotFoundException(ExceptionEnum.SHOP_RECOMMAND_ERROR);
            }

            Collections.shuffle(shopNameList);
            String shopName=shopNameList.get(0);

            /** Redis Access **/
            ShopRecommandResponseDTO redisDTO=shopLocationRedisService.searchShopNearByUser//예외처리
                    (shopName,shopRecommandRequestDTO.getLatitude(),shopRecommandRequestDTO.getLongitude());
            /** Validation **/
            if(redisDTO==null){
                throw new NotFoundException(ExceptionEnum.SHOP_RECOMMAND_ERROR);
            }

            /** RDB Access **/
            List<BenefitResponseDTO> benefitDTOList=cardBenefitRepository.findAllByUserIdAndShopName(userId,shopName);
            List<GiftResponseDTO> giftcardDTOList=giftcardRepository.findAllByUserIdAndShopName(userId,shopName);

            /** DTO Builder **/
            redisDTO.setBenefits(benefitDTOList);
            redisDTO.setGifts(giftcardDTOList);

            dto.add(redisDTO);
        }

        return dto;
    }
}
