package com.finp.moic.shop.adapter.out.persistence;

import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.domain.Shop;
import com.finp.moic.shop.application.port.out.QueryShopPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShopPersistenceAdapter implements QueryShopPersistencePort {

    private final ShopJpaRepository shopJpaRepository;
    private final ShopQuerydslRepository shopQuerydslRepository;

    @Override
    public Optional<Shop> findEntityByNameAndLocation(String shopName, String shopLocation) {
        return shopJpaRepository.findEntityByNameAndLocation(shopName,shopLocation);
    }

    @Override
    public Boolean exist(String shopName) {
        return shopQuerydslRepository.exist(shopName);
    }

    @Override
    public Optional<ShopDetailResponse> findByNameAndLocation(String shopName, String shopLocation) {
        return shopQuerydslRepository.findByNameAndLocation(shopName,shopLocation);
    }

    @Override
    public String findShopNameByKeyword(String keyword) {
        return shopQuerydslRepository.findShopNameByKeyword(keyword);
    }

    @Override
    public List<String> findAllShopNameByCategory(String category) {
        return shopQuerydslRepository.findAllShopNameByCategory(category);
    }

    @Override
    public List<String> findAllShopNameByMainCategoryAndSubCategory(String mainCategory, String subCategory) {
        return shopQuerydslRepository.findAllShopNameByMainCategoryAndSubCategory(mainCategory,subCategory);
    }

    @Override
    public Optional<Long> findSeqByNameAndLocation(String shopName, String shopLocation) {
        return shopQuerydslRepository.findSeqByNameAndLocation(shopName,shopLocation);
    }
}
