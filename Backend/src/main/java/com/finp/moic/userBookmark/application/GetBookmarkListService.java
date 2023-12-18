package com.finp.moic.userBookmark.application;

import com.finp.moic.userBookmark.application.port.in.GetBookmarkUseCase;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBookmarkListService implements GetBookmarkUseCase {

    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;
    @Override
    public List<UserBookmarkLookupServiceResponse> getBookmarkList(String userId) {

        /*** RDB Access ***/
        return queryUserBookmarkPersistencePort.findAllByUserId(userId);
    }
}
