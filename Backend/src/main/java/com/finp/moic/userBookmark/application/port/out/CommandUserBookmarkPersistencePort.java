package com.finp.moic.userBookmark.application.port.out;

import com.finp.moic.userBookmark.domain.UserBookmark;

public interface CommandUserBookmarkPersistencePort {

    UserBookmark save(UserBookmark userBookmark);
    void delete(UserBookmark userBookmark);

}
