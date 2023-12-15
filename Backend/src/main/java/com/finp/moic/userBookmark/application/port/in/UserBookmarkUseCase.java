package com.finp.moic.userBookmark.application.port.in;

import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkDeleteRequest;
import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkRegistRequest;
import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;

import java.util.List;

public interface UserBookmarkUseCase {
    void registBookmark(UserBookmarkRegistRequest userBookmarkRegistRequest, String userId);

    void deleteBookmarkList(UserBookmarkDeleteRequest userBookmarkDeleteRequest, String userId);

    List<UserBookmarkLookupServiceResponse> getBookmarkList(String userId);
}
