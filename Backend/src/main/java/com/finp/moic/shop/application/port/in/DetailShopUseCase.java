package com.finp.moic.shop.application.port.in;

import com.finp.moic.shop.adapter.in.request.ShopDetailRequest;
import com.finp.moic.shop.application.response.ShopDetailResponse;

public interface DetailShopUseCase {

    ShopDetailResponse detailShop(ShopDetailRequest request, String userId);
}
