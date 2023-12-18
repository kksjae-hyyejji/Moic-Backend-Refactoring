package com.finp.moic.shop.application.port.in;

import com.finp.moic.shop.adapter.in.request.ShopRecommandRequest;
import com.finp.moic.shop.application.response.ShopRecommandResponse;

import java.util.List;

public interface RecommandShopListUseCase {

    List<ShopRecommandResponse> recommandShopList(ShopRecommandRequest shopRecommandRequest, String userId);
}
