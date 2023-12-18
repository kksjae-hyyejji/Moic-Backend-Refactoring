package com.finp.moic.userBookmark.application;

import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import com.finp.moic.shop.domain.Shop;
import com.finp.moic.user.application.port.out.CommandUserPersistencePort;
import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import com.finp.moic.userBookmark.adapter.in.request.UserBookmarkRegistRequest;
import com.finp.moic.userBookmark.application.port.in.RegistBookmarkUseCase;
import com.finp.moic.userBookmark.application.port.out.CommandUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.domain.UserBookmark;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistBookmarkService implements RegistBookmarkUseCase {
    private final QueryShopPersistencePort queryShopPersistencePort;
    private final QueryUserBookmarkPersistencePort queryUserBookmarkPersistencePort;
    private final QueryUserPersistencePort queryUserPersistencePort;
    private final CommandUserBookmarkPersistencePort commandUserBookmarkPersistencePort;
    @Override
    public void registBookmark(UserBookmarkRegistRequest userBookmarkRegistRequest, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Shop shop=queryShopPersistencePort.findShopByNameAndLocation(userBookmarkRegistRequest.getShopName(), userBookmarkRegistRequest.getShopLocation())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));
        User user= queryUserPersistencePort.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** Validation ***/
        /* 혜지 : 한 사용자에 대해 중복된 북마크 등록 불가 */
        if(queryUserBookmarkPersistencePort.exist(user.getId(),shop.getName(),shop.getLocation()))
            throw new AlreadyExistException(ExceptionEnum.BOOKMARK_REGIST_DUPLICATE);

        /*** RDB Access ***/
        /* 혜지 : userBookmarkSeq 등의 기본 데이터셋 저장 */
        UserBookmark userBookmark=commandUserBookmarkPersistencePort.save(UserBookmark.builder()
                .build());

        /*** Entity Builder ***/
        userBookmark=UserBookmark.builder()
                .userBookmarkSeq(userBookmark.getUserBookmarkSeq())
                .shop(shop)
                .user(user)
                .build();

        /*** RDB Access ***/
        /* 혜지 : userBookmark FK 저장 */
        commandUserBookmarkPersistencePort.save(userBookmark);

    }
}
