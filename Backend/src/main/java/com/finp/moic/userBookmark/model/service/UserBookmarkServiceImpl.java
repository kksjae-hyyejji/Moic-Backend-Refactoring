package com.finp.moic.userBookmark.model.service;

import com.finp.moic.card.model.entity.Card;
import com.finp.moic.card.model.entity.UserCard;
import com.finp.moic.shop.model.entity.Shop;
import com.finp.moic.shop.model.repository.ShopRepository;
import com.finp.moic.user.model.entity.User;
import com.finp.moic.user.model.repository.UserRepository;
import com.finp.moic.userBookmark.model.dto.request.ShopRequestDTO;
import com.finp.moic.userBookmark.model.dto.request.UserBookmarkDeleteRequestDTO;
import com.finp.moic.userBookmark.model.dto.request.UserBookmarkRegistRequestDTO;
import com.finp.moic.userBookmark.model.dto.response.UserBookmarkLookupResponseDTO;
import com.finp.moic.userBookmark.model.entity.UserBookmark;
import com.finp.moic.userBookmark.model.repository.UserBookmarkRepository;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.AlreadyExistException;
import com.finp.moic.util.exception.list.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserBookmarkServiceImpl implements UserBookmarkService{

    private final UserBookmarkRepository userBookmarkRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public UserBookmarkServiceImpl(UserBookmarkRepository userBookmarkRepository, UserRepository userRepository,
                                   ShopRepository shopRepository) {
        this.userBookmarkRepository = userBookmarkRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    public void registBookmark(UserBookmarkRegistRequestDTO userBookmarkRegistRequestDTO, String userId) {

        /**
         * TO DO :: SOFT DELETE 확인해, 존재 시 회복하기
         * */

        /*** RDB Access ***/
        Shop shop=shopRepository.findEntityByNameAndLocation(userBookmarkRegistRequestDTO.getShopName(),userBookmarkRegistRequestDTO.getShopLocation())
                .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** Validation ***/
        /* 혜지 : 한 사용자에 대해 중복된 북마크 등록 불가 */
        if(userBookmarkRepository.exist(user.getId(),shop.getName(),shop.getLocation()))
            throw new AlreadyExistException(ExceptionEnum.BOOKMARK_REGIST_DUPLICATE);

        /*** RDB Access ***/
        /* 혜지 : userBookmarkSeq 등의 기본 데이터셋 저장 */
        UserBookmark userBookmark=userBookmarkRepository.save(UserBookmark.builder()
                .build());

        /*** Entity Builder ***/
        userBookmark=UserBookmark.builder()
                .userBookmarkSeq(userBookmark.getUserBookmarkSeq())
                .shop(shop)
                .user(user)
                .build();

        /*** RDB Access ***/
        /* 혜지 : userBookmark FK 저장 */
        userBookmarkRepository.save(userBookmark);

    }

    @Override
    public void deleteBookmarkList(UserBookmarkDeleteRequestDTO userBookmarkDeleteRequestDTO, String userId) {

        /*** Validation ***/
        User user=userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(ExceptionEnum.USER_NOT_FOUND));

        /*** RDB Access ***/
        for(ShopRequestDTO shopDTO:userBookmarkDeleteRequestDTO.getShopList()){
            Long shopSeq=shopRepository.findSeqByNameAndLocation(shopDTO.getShopName(),shopDTO.getShopLocation())
                    .orElseThrow(()->new NotFoundException(ExceptionEnum.SHOP_NOT_FOUND));

            UserBookmark userBookmark=userBookmarkRepository.findByUserIdAndShopSeq(user.getId(),shopSeq)
                    .orElseThrow(()->new NotFoundException(ExceptionEnum.BOOKMARK_DELETE_ERROR));
            userBookmarkRepository.delete(userBookmark);
        }

    }

    @Override
    public List<UserBookmarkLookupResponseDTO> getBookmarkList(String userId) {

        /*** RDB Access ***/
        List<UserBookmarkLookupResponseDTO> dto=userBookmarkRepository.findAllByUserId(userId);

        return dto;
    }
}
