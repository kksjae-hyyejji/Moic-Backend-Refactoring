package com.finp.moic.userBookmark.adapter.out.persistence;

import com.finp.moic.userBookmark.application.response.QUserBookmarkLookupServiceResponse;
import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;
import com.finp.moic.userBookmark.domain.QUserBookmark;
import com.finp.moic.userBookmark.domain.UserBookmark;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserBookmarkQuerydslRepository {

    private final JPAQueryFactory queryFactory;

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

    public List<UserBookmarkLookupServiceResponse> findAllByUserId(String userId) {
        QUserBookmark userBookmark=QUserBookmark.userBookmark;

        return queryFactory
                .select(
                        new QUserBookmarkLookupServiceResponse(
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

    public Optional<UserBookmark> findByUserIdAndShopSeq(String id, Long shopSeq) {
        QUserBookmark userBookmark=QUserBookmark.userBookmark;

        return Optional.ofNullable(queryFactory
                .selectFrom(userBookmark)
                .where(userBookmark.user.id.eq(id)
                        .and(userBookmark.shop.shopSeq.eq(shopSeq)))
                .fetchOne());
    }
}
