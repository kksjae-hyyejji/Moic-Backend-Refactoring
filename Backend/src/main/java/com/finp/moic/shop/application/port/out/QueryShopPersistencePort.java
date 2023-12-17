package com.finp.moic.shop.application.port.out;

import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.domain.Shop;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QueryShopPersistencePort {
    Optional<Shop> findShopByNameAndLocation(@Param("shopName") String shopName, @Param("shopLocation") String shopLocation);

    Boolean exist(String shopName);

    Optional<ShopDetailResponse> findShopDetailDTOByNameAndLocation(String shopName, String shopLocation);

    String findShopNameByKeyword(String keyword);

    List<String> findShopNameListByCategory(String category);

    List<String> findShopNameListByMainCategoryAndSubCategory(String mainCategory, String subCategory);

    Optional<Long> findShopSeqByNameAndLocation(String shopName, String shopLocation);
}
