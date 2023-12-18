package com.finp.moic.shop.application;

import com.finp.moic.card.application.port.out.QueryCardBenefitPersistencePort;
import com.finp.moic.giftCard.application.port.out.QueryGiftcardPersistencePort;
import com.finp.moic.shop.adapter.in.request.ShopDetailRequest;
import com.finp.moic.shop.application.port.in.DetailShopUseCase;
import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.shop.application.response.ShopCardBenefitResponseInShopDetailResponse;
import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.application.response.ShopGiftCardResponseInShopDetailResponse;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailShopService implements DetailShopUseCase {

    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryGiftcardPersistencePort queryGiftcardPersistencePort;
    private final QueryCardBenefitPersistencePort queryCardBenefitPersistencePort;
    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;

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
}
