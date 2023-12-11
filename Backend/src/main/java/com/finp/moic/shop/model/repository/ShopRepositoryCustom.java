package com.finp.moic.shop.model.repository;

import com.finp.moic.shop.model.dto.response.ShopDetailResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShopRepositoryCustom {

    Boolean exist(String shopName);

    Optional<ShopDetailResponseDTO> findByNameAndLocation(String shopName, String shopLocation);

    String findShopNameByKeyword(String keyword);

    List<String> findAllShopNameByCategory(String category);

    List<String> findAllShopNameByMainCategoryAndSubCategory(String mainCategory, String subCategory);

    Optional<Long> findSeqByNameAndLocation(String shopName, String shopLocation);
}
