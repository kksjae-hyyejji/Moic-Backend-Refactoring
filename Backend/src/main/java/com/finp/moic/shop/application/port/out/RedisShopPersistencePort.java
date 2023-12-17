package com.finp.moic.shop.application.port.out;

import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.shop.application.response.ShopSearchResponse;
import com.finp.moic.shop.domain.Shop;

import java.util.List;

public interface RedisShopPersistencePort {

    void setGeoShopList(List<Shop> shopList);
    List<ShopSearchResponse> searchShopListNearByUser(String shopName, double latitude, double longitude);
    ShopRecommandResponse findShopNearByUser(String shopName, double latitude, double longitude);
    ShopRecommandResponse findShopOnTheWhole(String shopName, double latitude, double longitude);

}
