package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.QGiftcard;
import com.finp.moic.giftCard.application.response.QGiftcardListServiceResponse;
import com.finp.moic.shop.application.response.QShopGiftCardResponseInShopDetailResponse;
import com.finp.moic.shop.application.response.ShopGiftCardResponseInShopDetailResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GiftcardQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<ShopGiftCardResponseInShopDetailResponse> findAllByUserIdAndShopName(String userId, String shopName) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return jpaQueryFactory
                .select(
                        new QShopGiftCardResponseInShopDetailResponse(
                                giftcard.imageUrl,
                                giftcard.dueDate
                        )
                )
                .from(giftcard)
                .where(
                        giftcard.user.id.eq(userId)
                        .and(giftcard.shopName.eq(shopName))
                )
                .fetch();
    }

    public List<String> findAllShopNameByUserId(String userId) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return jpaQueryFactory
                .select(giftcard.shopName)
                .from(giftcard)
                .where(giftcard.user.id.eq(userId))
                .fetch();
    }

    public List<GiftcardListServiceResponse> findAllByUserId(String userId) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return jpaQueryFactory
                .select(
                        new QGiftcardListServiceResponse(
                                giftcard.giftcardSeq,
                                giftcard.imageUrl,
                                giftcard.dueDate
                        )
                )
                .from(giftcard)
                .where(giftcard.user.id.eq(userId))
                .fetch();
    }
}
