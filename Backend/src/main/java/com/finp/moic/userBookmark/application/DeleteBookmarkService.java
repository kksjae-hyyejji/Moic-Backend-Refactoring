package com.finp.moic.userBookmark.application;

import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.userBookmark.adapter.in.request.ShopRequest;
import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkDeleteRequest;
import com.finp.moic.userBookmark.application.port.in.DeleteBookmarkUseCase;
import com.finp.moic.userBookmark.application.port.out.CommandUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.domain.UserBookmark;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookmarkService implements DeleteBookmarkUseCase {

    private final QueryUserPersistencePort queryUserPersistencePort;
    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;
    private final CommandUserBookmarkPersistencePort commandUserBookmarkPersistencePort;
    @Override
    public void deleteBookmarkList(UserBookmarkDeleteRequest userBookmarkDeleteRequest, String userId) {

        /*** Validation ***/
        User user= queryUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** RDB Access ***/
        for(ShopRequest shopDTO: userBookmarkDeleteRequest.getShopList()){
            Long shopSeq=queryShopPersistencePort.findShopSeqByNameAndLocation(shopDTO.getShopName(),shopDTO.getShopLocation())
                    .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));

            UserBookmark userBookmark=queryUserBookmarkPersistencePort.findByUserIdAndShopSeq(user.getId(),shopSeq)
                    .orElseThrow(()->new NotFoundException(ExceptionEnum.BOOKMARK_DELETE_ERROR));
            commandUserBookmarkPersistencePort.delete(userBookmark);
        }

    }
}
