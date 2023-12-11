package com.finp.moic.shop.model.service;

import com.finp.moic.shop.model.dto.request.ShopRecommandRequestDTO;
import com.finp.moic.shop.model.dto.response.ShopDetailResponseDTO;
import com.finp.moic.shop.model.dto.response.ShopRecommandResponseDTO;
import com.finp.moic.shop.model.dto.response.ShopSearchResponseDTO;

import java.util.List;

public interface ShopService {

    ShopDetailResponseDTO detailShop(String shopName, String ShopLocation, String userId);

    List<ShopSearchResponseDTO> searchShop(String keyword, double latitude, double longitude, String userId);

    List<ShopSearchResponseDTO> getShopListByCategory(String category, double latitude, double longitude, String userId);

    List<ShopRecommandResponseDTO> recommandShopList(ShopRecommandRequestDTO shopRecommandRequestDTO, String userId);
}
