package com.finp.moic.shop.application.port.in;

import com.finp.moic.shop.adapter.in.request.ShopDetailRequest;
import com.finp.moic.shop.adapter.in.request.ShopRecommandRequest;
import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.shop.application.response.ShopSearchResponse;

import java.util.List;

public interface ShopUseCase {

    ShopDetailResponse detailShop(ShopDetailRequest request, String userId);

    List<ShopSearchResponse> searchShopListByKeyword(String keyword, double latitude, double longitude, String userId);

    List<ShopSearchResponse> searchShopListByCategory(String category, double latitude, double longitude, String userId);

    List<ShopRecommandResponse> recommandShopList(ShopRecommandRequest shopRecommandRequest, String userId);
}
