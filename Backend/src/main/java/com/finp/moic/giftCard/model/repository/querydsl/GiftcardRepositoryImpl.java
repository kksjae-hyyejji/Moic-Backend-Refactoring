package com.finp.moic.giftCard.model.repository.querydsl;

import com.finp.moic.giftCard.model.dto.response.GiftcardListResponseDTO;
import com.finp.moic.giftCard.model.dto.response.QGiftcardListResponseDTO;
import com.finp.moic.giftCard.model.entity.Giftcard;
import com.finp.moic.giftCard.model.entity.QGiftcard;
import com.finp.moic.shop.model.dto.response.GiftResponseDTO;
import com.finp.moic.shop.model.dto.response.QGiftResponseDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GiftcardRepositoryImpl implements GiftcardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public GiftcardRepositoryImpl(JPAQueryFactory queryFactory) {
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

    public List<GiftcardListResponseDTO> findAllByUserId(String userId) {
        QGiftcard giftcard=QGiftcard.giftcard;

        return queryFactory
                .select(
                        new QGiftcardListResponseDTO(
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
