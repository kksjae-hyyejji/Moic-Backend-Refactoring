package com.finp.moic.user.adpater.out.persistence;

import com.finp.moic.user.application.port.out.QueryUserPersistencePort;
import com.finp.moic.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistneceAdapter implements com.finp.moic.user.application.port.out.CommandUserPersistencePort, QueryUserPersistencePort {

    private final UserJpaRepository userJpaRepository;
    @Override
    public Optional<User> findById(String id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public User findByNameAndEmail(String name, String email) {
        return userJpaRepository.findByNameAndEmail(name,email);
    }

    @Override
    public User findByIdAndNameAndEmail(String id, String name, String email) {
        return userJpaRepository.findByIdAndNameAndEmail(id,name,email);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userJpaRepository.delete(user);
    }
}
