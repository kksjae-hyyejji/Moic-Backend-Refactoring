package com.finp.moic.userBookmark.application.port.in;

import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;

import java.util.List;

public interface GetBookmarkUseCase {

    List<UserBookmarkLookupServiceResponse> getBookmarkList(String userId);

}
