package com.finp.moic.userBookmark.model.service;

import com.finp.moic.userBookmark.model.dto.request.UserBookmarkDeleteRequestDTO;
import com.finp.moic.userBookmark.model.dto.request.UserBookmarkRegistRequestDTO;
import com.finp.moic.userBookmark.model.dto.response.UserBookmarkLookupResponseDTO;

import java.util.List;

public interface UserBookmarkService {
    void registBookmark(UserBookmarkRegistRequestDTO userBookmarkRegistRequestDTO, String userId);

    void deleteBookmarkList(UserBookmarkDeleteRequestDTO userBookmarkDeleteRequestDTO, String userId);

    List<UserBookmarkLookupResponseDTO> getBookmarkList(String userId);
}
