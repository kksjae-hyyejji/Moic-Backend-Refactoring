package com.finp.moic.shop.application.port.out;

import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.domain.Shop;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QueryShopPersistencePort {
    Optional<Shop> findEntityByNameAndLocation(@Param("shopName") String shopName, @Param("shopLocation") String shopLocation);

    Boolean exist(String shopName);

    Optional<ShopDetailResponse> findByNameAndLocation(String shopName, String shopLocation);

    String findShopNameByKeyword(String keyword);

    List<String> findAllShopNameByCategory(String category);

    List<String> findAllShopNameByMainCategoryAndSubCategory(String mainCategory, String subCategory);

    Optional<Long> findSeqByNameAndLocation(String shopName, String shopLocation);
}
