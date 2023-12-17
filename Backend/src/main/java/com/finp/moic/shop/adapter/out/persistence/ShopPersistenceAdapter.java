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
    public Optional<Shop> findShopByNameAndLocation(String shopName, String shopLocation) {
        return shopJpaRepository.findByNameAndLocation(shopName,shopLocation);
    }

    @Override
    public Boolean exist(String shopName) {
        return shopQuerydslRepository.exist(shopName);
    }

    @Override
    public Optional<ShopDetailResponse> findShopDetailDTOByNameAndLocation(String shopName, String shopLocation) {
        return shopQuerydslRepository.findByNameAndLocation(shopName,shopLocation);
    }

    @Override
    public String findShopNameByKeyword(String keyword) {
        return shopQuerydslRepository.findByKeyword(keyword);
    }

    @Override
    public List<String> findShopNameListByCategory(String category) {
        return shopQuerydslRepository.findAllByCategory(category);
    }

    @Override
    public List<String> findShopNameListByMainCategoryAndSubCategory(String mainCategory, String subCategory) {
        return shopQuerydslRepository.findAllByMainCategoryAndSubCategory(mainCategory,subCategory);
    }

    @Override
    public Optional<Long> findShopSeqByNameAndLocation(String shopName, String shopLocation) {
        return shopQuerydslRepository.findIdByNameAndLocation(shopName,shopLocation);
    }
}
