package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import com.finp.moic.giftCard.application.response.QGiftcardBrandServiceResponse;
import com.finp.moic.giftCard.domain.QGiftcardBrand;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GiftcardBrandQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public GiftcardBrandServiceResponse findByName(String shopName) {
        QGiftcardBrand giftcardBrand = QGiftcardBrand.giftcardBrand;

        return jpaQueryFactory
                .select(
                        new QGiftcardBrandServiceResponse(
                                giftcardBrand.mainCategory,
                                giftcardBrand.category
                        )
                )
                .from(giftcardBrand)
                .where(giftcardBrand.name.contains(shopName))
                .fetchOne();
    }
}
