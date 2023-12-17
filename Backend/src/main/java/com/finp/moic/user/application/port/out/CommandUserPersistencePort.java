package com.finp.moic.user.application.port.out;

import com.finp.moic.user.domain.User;

public interface CommandUserPersistencePort {

    User save(User user);

    void delete(User user);
}
