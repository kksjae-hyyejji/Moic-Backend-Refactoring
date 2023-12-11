package com.finp.moic.shop.controller;

import com.finp.moic.shop.model.dto.request.ShopRecommandRequestDTO;
import com.finp.moic.shop.model.dto.response.ShopDetailResponseDTO;
import com.finp.moic.shop.model.dto.response.ShopRecommandResponseDTO;
import com.finp.moic.shop.model.dto.response.ShopSearchResponseDTO;
import com.finp.moic.shop.model.service.ShopService;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.util.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Validated
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/map/shops/detail")
    public ResponseEntity<ResponseDTO> detailShop(@RequestParam("shopName") @NotBlank String shopName,
                                                  @RequestParam("shopLocation") @NotNull String shopLocation,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        ShopDetailResponseDTO response= shopService.detailShop(shopName, shopLocation, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드혜택, 기프티콘 가맹점 상세 조회")
                .data(response)
                .build());
    }

    @GetMapping("/map/shops")
    public ResponseEntity<ResponseDTO> searchShop(@RequestParam("keyword") @NotNull String keyword,
                                                  @RequestParam("latitude") @Positive double latitude,
                                                  @RequestParam("longitude") @Positive double longitude,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication){

        List<ShopSearchResponseDTO> dto= shopService.searchShop(keyword,latitude,longitude, userAuthentication.getId());
        HashMap<String, Object> response=new HashMap<>();
        response.put("shopList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드혜택, 기프티콘 가맹점 조회")
                .data(response)
                .build());
    }

    @GetMapping("/map/category")
    public ResponseEntity<ResponseDTO> getShopListByCategory(@RequestParam("category") @NotBlank String category,
                                                             @RequestParam("latitude") @Positive double latitude,
                                                             @RequestParam("longitude") @Positive double longitude,
                                                                @AuthenticationPrincipal UserAuthentication userAuthentication){

        List<ShopSearchResponseDTO> dto= shopService.getShopListByCategory(category,latitude,longitude, userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("shopList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("카테고리별 조회")
                .data(response)
                .build());
    }

    @PostMapping("/map/rec")
    public ResponseEntity<ResponseDTO> recommandShopList(@Valid @RequestBody ShopRecommandRequestDTO shopRecommandRequestDTO,
                                                             @AuthenticationPrincipal UserAuthentication userAuthentication){
        List<ShopRecommandResponseDTO> dto= shopService.recommandShopList(shopRecommandRequestDTO, userAuthentication.getId());
        HashMap<String,Object> response=new HashMap<>();
        response.put("shopList",dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("경로 추천")
                .data(response)
                .build());
    }

}
