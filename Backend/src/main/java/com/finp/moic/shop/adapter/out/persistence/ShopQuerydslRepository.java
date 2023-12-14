package com.finp.moic.shop.adapter.out.persistence;

import com.finp.moic.shop.application.response.QShopDetailResponse;
import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.domain.QShop;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ShopQuerydslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public Boolean exist(String shopName){
        QShop shop=QShop.shop;

        Integer fetchOne = jpaQueryFactory
                .selectOne()
                .from(shop)
                .where(shop.name.eq(shopName))
                .fetchFirst();

        return fetchOne != null;
    }

    public Optional<ShopDetailResponse> findByNameAndLocation(String shopName, String shopLocation) {
        QShop shop=QShop.shop;

        return Optional.ofNullable(jpaQueryFactory
                .select(
                        new QShopDetailResponse(
                                shop.category,
                                shop.name.as("shopName"),
                                shop.location.as("shopLocation"),
                                shop.address
                        )
                )
                .from(shop)
                .where(
                        shop.name.eq(shopName)
                                .and(shop.location.eq(shopLocation))
                )
                .fetchOne());
    }

    /**
     * CONFIRM :: 한 키워드에 대해 다양한 가맹점이 있다면?
     **/
    public String findShopNameByKeyword(String keyword) {
        QShop shop=QShop.shop;

        return jpaQueryFactory
                .select(shop.name)
                .from(shop)
                .where(shop.name.contains(keyword))
                .fetchFirst();
    }

    public List<String> findAllShopNameByCategory(String category) {
        QShop shop=QShop.shop;

        return jpaQueryFactory
                .selectDistinct(shop.name)
                .from(shop)
                .where(
                        shop.mainCategory.contains(category)
                                .or(shop.category.contains(category))
                )
                .fetch();
    }

    public List<String> findAllShopNameByMainCategoryAndSubCategory(String mainCategory, String subCategory) {
        QShop shop=QShop.shop;

        return jpaQueryFactory
                .selectDistinct(shop.name)
                .from(shop)
                .where(
                        shop.mainCategory.eq(mainCategory)
                                .and(shop.category.eq(subCategory))
                )
                .fetch();
    }

    public Optional<Long> findSeqByNameAndLocation(String shopName, String shopLocation) {
        QShop shop=QShop.shop;

        return Optional.ofNullable(jpaQueryFactory
                .select(shop.shopSeq)
                .from(shop)
                .where(
                        shop.name.contains(shopName)
                                .and(shop.location.contains(shopLocation))
                )
                .fetchOne());
    }
}
