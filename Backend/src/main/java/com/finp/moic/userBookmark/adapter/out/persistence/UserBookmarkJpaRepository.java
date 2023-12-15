package com.finp.moic.userBookmark.adapter.out.persistence;

import com.finp.moic.userBookmark.domain.UserBookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookmarkJpaRepository extends JpaRepository<UserBookmark,Long> {
}
