package com.finp.moic.card.adapter.out.persistence;

import com.finp.moic.card.application.port.out.CommandUserCardPersistencePort;
import com.finp.moic.card.application.port.out.QueryUserCardPersistencePort;
import com.finp.moic.card.application.response.CardMineServiceResponse;
import com.finp.moic.card.domain.UserCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCardPersistenceAdapter implements CommandUserCardPersistencePort, QueryUserCardPersistencePort {

    private final UserCardJpaRepository jpaRepository;
    private final UserCardQuerydslRepository querydslRepository;

    @Override
    public UserCard save(UserCard userCard) {
        return jpaRepository.save(userCard);
    }

    @Override
    public void delete(UserCard userCard) {
        jpaRepository.delete(userCard);
    }

    @Override
    public boolean exist(String userId, String cardName) {
        return querydslRepository.exist(userId, cardName);
    }

    @Override
    public List<String> findAllCardNameByUserId(String userId) {
        return querydslRepository.findAllCardNameByUserId(userId);
    }

    @Override
    public List<CardMineServiceResponse> findAllByUserId(String userId) {
        return querydslRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<UserCard> findByUserIdAndCardName(String userId, String cardName) {
        return jpaRepository.findByUserIdAndCardName(userId,cardName);
    }
}
