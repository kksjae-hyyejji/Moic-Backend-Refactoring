package com.finp.moic.user.model.repository;


import com.finp.moic.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);
    User findByEmail(String email);
    User findByNameAndEmail(String name, String email);
    User findByIdAndNameAndEmail(String id, String name, String email);
}
