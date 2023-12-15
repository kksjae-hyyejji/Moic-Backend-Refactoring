package com.finp.moic.shop.application.port.in;

import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.shop.application.response.ShopSearchResponse;
import com.finp.moic.shop.domain.Shop;

import java.util.List;

public interface ShopRedisUseCase {

    void setGeoShopList(List<Shop> shopList);
    List<ShopSearchResponse> searchShopListNearByUser(String shopName,double latitude,double longitude);
    ShopRecommandResponse searchShopNearByUser(String shopName,double latitude,double longitude);
    ShopRecommandResponse searchShop(String shopName,double latitude,double longitude);

}
