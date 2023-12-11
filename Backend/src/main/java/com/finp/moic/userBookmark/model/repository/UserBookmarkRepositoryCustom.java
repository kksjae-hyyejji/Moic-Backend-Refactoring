package com.finp.moic.userBookmark.model.repository;

import com.finp.moic.userBookmark.model.dto.response.UserBookmarkLookupResponseDTO;
import com.finp.moic.userBookmark.model.entity.UserBookmark;

import java.util.List;
import java.util.Optional;

public interface UserBookmarkRepositoryCustom {

    boolean exist(String id, String name, String location);

    List<UserBookmarkLookupResponseDTO> findAllByUserId(String userId);

    Optional<UserBookmark> findByUserIdAndShopSeq(String id, Long shopSeq);
}
