package com.finp.moic.userBookmark.model.repository;

import com.finp.moic.card.model.entity.QUserCard;
import com.finp.moic.shop.model.entity.QShop;
import com.finp.moic.userBookmark.model.dto.response.QUserBookmarkLookupResponseDTO;
import com.finp.moic.userBookmark.model.dto.response.UserBookmarkLookupResponseDTO;
import com.finp.moic.userBookmark.model.entity.QUserBookmark;
import com.finp.moic.userBookmark.model.entity.UserBookmark;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserBookmarkRepositoryImpl implements UserBookmarkRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Autowired
    public UserBookmarkRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean exist(String id, String name, String location) {
        QUserBookmark userBookmark=QUserBookmark.userBookmark;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(userBookmark)
                .where(
                        userBookmark.user.id.eq(id)
                                .and(userBookmark.shop.name.eq(name))
                                .and(userBookmark.shop.location.eq(location))
                )
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<UserBookmarkLookupResponseDTO> findAllByUserId(String userId) {
        QUserBookmark userBookmark=QUserBookmark.userBookmark;

        return queryFactory
                .select(
                        new QUserBookmarkLookupResponseDTO(
                                userBookmark.shop.category.as("category"),
                                userBookmark.shop.name.as("shopName"),
                                userBookmark.shop.location.as("shopLocation"),
                                userBookmark.shop.address.as("address"),
                                userBookmark.shop.latitude.as("latitude"),
                                userBookmark.shop.longitude.as("longitude")
                        )
                )
                .from(userBookmark)
                .where(userBookmark.user.id.eq(userId))
                .fetch();
    }

    @Override
    public Optional<UserBookmark> findByUserIdAndShopSeq(String id, Long shopSeq) {
        QUserBookmark userBookmark=QUserBookmark.userBookmark;

        return Optional.ofNullable(queryFactory
                .selectFrom(userBookmark)
                .where(userBookmark.user.id.eq(id)
                        .and(userBookmark.shop.shopSeq.eq(shopSeq)))
                .fetchOne());
    }
}
