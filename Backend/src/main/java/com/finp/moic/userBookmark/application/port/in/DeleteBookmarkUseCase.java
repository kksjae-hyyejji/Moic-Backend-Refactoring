package com.finp.moic.userBookmark.application.port.in;

import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkDeleteRequest;

public interface DeleteBookmarkUseCase {

    void deleteBookmarkList(UserBookmarkDeleteRequest userBookmarkDeleteRequest, String userId);

}
