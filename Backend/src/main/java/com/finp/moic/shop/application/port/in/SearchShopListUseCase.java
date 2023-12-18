package com.finp.moic.shop.application.port.in;

import com.finp.moic.shop.application.response.ShopSearchResponse;

import java.util.List;

public interface SearchShopListUseCase {

    List<ShopSearchResponse> searchShopListByKeyword(String keyword, double latitude, double longitude, String userId);

    List<ShopSearchResponse> searchShopListByCategory(String category, double latitude, double longitude, String userId);
}
