package com.finp.moic.shop.model.repository;

import com.finp.moic.shop.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long>, ShopRepositoryCustom {

    @Query(value = "SELECT * FROM shop WHERE name=:shopName AND location=:shopLocation", nativeQuery = true)
    Optional<Shop> findEntityByNameAndLocation(@Param("shopName") String shopName, @Param("shopLocation") String shopLocation);
}
