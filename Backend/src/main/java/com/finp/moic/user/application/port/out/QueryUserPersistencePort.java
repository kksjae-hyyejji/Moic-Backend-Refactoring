package com.finp.moic.user.application.port.out;

import com.finp.moic.user.domain.User;

import java.util.Optional;

public interface QueryUserPersistencePort {

    Optional<User> findById(String id);
    User findByEmail(String email);
    User findByNameAndEmail(String name, String email);
    User findByIdAndNameAndEmail(String id, String name, String email);
}
