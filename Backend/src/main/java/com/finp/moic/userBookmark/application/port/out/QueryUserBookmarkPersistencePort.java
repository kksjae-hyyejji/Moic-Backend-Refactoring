package com.finp.moic.userBookmark.application.port.out;

import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;
import com.finp.moic.userBookmark.domain.UserBookmark;

import java.util.List;
import java.util.Optional;

public interface QueryUserBookmarkPersistencePort {

    boolean exist(String id,String name,String location);
    List<UserBookmarkLookupServiceResponse> findAllByUserId(String userId);
    Optional<UserBookmark> findByUserIdAndShopSeq(String id, Long shopSeq);

}
