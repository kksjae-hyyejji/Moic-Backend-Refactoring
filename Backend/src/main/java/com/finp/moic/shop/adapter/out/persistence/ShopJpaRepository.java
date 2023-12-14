package com.finp.moic.shop.adapter.out.persistence;

import com.finp.moic.shop.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopJpaRepository extends JpaRepository<Shop,Long> {

    @Query(value = "SELECT * FROM shop WHERE name=:shopName AND location=:shopLocation", nativeQuery = true)
    Optional<Shop> findEntityByNameAndLocation(@Param("shopName") String shopName, @Param("shopLocation") String shopLocation);

}
