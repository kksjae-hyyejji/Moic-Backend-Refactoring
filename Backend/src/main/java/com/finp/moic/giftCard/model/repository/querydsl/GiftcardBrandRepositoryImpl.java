package com.finp.moic.giftCard.model.repository.querydsl;

import com.finp.moic.giftCard.model.dto.response.GiftcardBrandResponseDTO;
import com.finp.moic.giftCard.model.dto.response.QGiftcardBrandResponseDTO;
import com.finp.moic.giftCard.model.entity.QGiftcardBrand;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GiftcardBrandRepositoryImpl implements GiftcardBrandRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public GiftcardBrandRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public GiftcardBrandResponseDTO findByName(String shopName) {
        QGiftcardBrand giftcardBrand=QGiftcardBrand.giftcardBrand;

        return queryFactory
                .select(
                        new QGiftcardBrandResponseDTO(
                                giftcardBrand.mainCategory,
                                giftcardBrand.category
                        )
                )
                .from(giftcardBrand)
                .where(giftcardBrand.name.contains(shopName))
                .fetchOne();
    }
}
