package com.finp.moic.userBookmark.application.port.in;

import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkRegistRequest;

public interface RegistBookmarkUseCase {

    void registBookmark(UserBookmarkRegistRequest userBookmarkRegistRequest, String userId);

}
