package com.finp.moic.giftCard.adapter.out.persistence;

import com.finp.moic.giftCard.application.port.out.GiftcardBrandPersistenceQueryPort;
import com.finp.moic.giftCard.application.response.GiftcardBrandServiceResponse;
import com.finp.moic.giftCard.application.response.QGiftcardBrandServiceResponse;
import com.finp.moic.giftCard.domain.QGiftcardBrand;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GifrcardBrandPersistencePortImpl implements GiftcardBrandPersistenceQueryPort {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public GifrcardBrandPersistencePortImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GiftcardBrandServiceResponse findByName(String shopName) {
        QGiftcardBrand giftcardBrand=QGiftcardBrand.giftcardBrand;

        return queryFactory
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
