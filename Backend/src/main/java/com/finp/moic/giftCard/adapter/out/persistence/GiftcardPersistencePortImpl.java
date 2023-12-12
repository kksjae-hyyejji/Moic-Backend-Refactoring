package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.port.out.GiftcardPresistenceQueryPort;
import com.finp.moic.giftCard.application.response.GiftcardListServiceResponse;
import com.finp.moic.giftCard.domain.QGiftcard;
import com.finp.moic.giftCard.application.response.QGiftcardListServiceResponse;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;
import com.finp.moic.shop.model.dto.response.QGiftResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftcardPersistencePortImpl implements GiftcardPresistenceQueryPort {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public GiftcardPersistencePortImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<GiftResponseDTO> findAllByUserIdAndShopName(String userId, String shopName) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return queryFactory
                .select(
                        new QGiftResponseDTO(
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

    @Override
    public List<String> findAllShopNameByUserId(String userId) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return queryFactory
                .select(giftcard.shopName)
                .from(giftcard)
                .where(giftcard.user.id.eq(userId))
                .fetch();
    }

    public List<GiftcardListServiceResponse> findAllByUserId(String userId) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return queryFactory
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
