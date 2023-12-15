package com.finp.moic.userBookmark.adapter.out.persistence;

import com.finp.moic.userBookmark.application.port.out.CommandUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.application.port.out.QueryUserBookmarkPersistencePort;
import com.finp.moic.userBookmark.application.response.UserBookmarkLookupServiceResponse;
import com.finp.moic.userBookmark.domain.UserBookmark;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserBookmarkPersistenceAdapter implements CommandUserBookmarkPersistencePort, QueryUserBookmarkPersistencePort {

    private final UserBookmarkJpaRepository jpaRepository;
    private final UserBookmarkQuerydslRepository querydslRepository;

    @Override
    public UserBookmark save(UserBookmark userBookmark) {
        return jpaRepository.save(userBookmark);
    }

    @Override
    public void delete(UserBookmark userBookmark) {
        jpaRepository.delete(userBookmark);
    }

    @Override
    public boolean exist(String id, String name, String location) {
        return querydslRepository.exist(id,name,location);
    }

    @Override
    public List<UserBookmarkLookupServiceResponse> findAllByUserId(String userId) {
        return querydslRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserBookmark> findByUserIdAndShopSeq(String id, Long shopSeq) {
        return querydslRepository.findByUserIdAndShopSeq(id,shopSeq);
    }
}
