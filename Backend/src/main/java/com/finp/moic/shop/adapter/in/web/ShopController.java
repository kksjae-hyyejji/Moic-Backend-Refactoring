package com.finp.moic.shop.adapter.in.web;

import com.finp.moic.shop.adapter.in.request.ShopDetailRequest;
import com.finp.moic.shop.adapter.in.request.ShopRecommandRequest;
import com.finp.moic.shop.application.response.ShopDetailResponse;
import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.shop.application.response.ShopSearchResponse;
import com.finp.moic.shop.application.port.in.ShopUseCase;
import com.finp.moic.util.dto.ResponseDTO;
import com.finp.moic.user.security.dto.UserAuthentication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class ShopController {

    private final ShopUseCase shopUseCase;

    /** 가맹점 상세 조회 API **/
    @GetMapping("/map/shops/detail")
    public ResponseEntity<ResponseDTO> detailShop(@RequestParam("shopName") @NotBlank String shopName,
                                                  @RequestParam("shopLocation") @NotNull String shopLocation,
                                                  @AuthenticationPrincipal UserAuthentication userAuthentication){

        ShopDetailRequest request= new ShopDetailRequest(shopName, shopLocation);
        ShopDetailResponse response= shopUseCase.detailShop(request, userAuthentication.getId());

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드혜택, 기프티콘 가맹점 상세 조회")
                .data(response)
                .build());
    }

    /** 주변 가맹점 검색 API **/
    @GetMapping("/map/shops")
    public ResponseEntity<ResponseDTO> searchShopList(@RequestParam("keyword") @NotNull String keyword,
                                                  @RequestParam("latitude") @Positive double latitude,
                                                  @RequestParam("longitude") @Positive double longitude,
                                                    @AuthenticationPrincipal UserAuthentication userAuthentication){

        List<ShopSearchResponse> dto= shopUseCase.searchShopListByKeyword(keyword, latitude, longitude, userAuthentication.getId());
        HashMap<String, Object> response= new HashMap<>();
        response.put("shopList", dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("내 카드혜택, 기프티콘 가맹점 조회")
                .data(response)
                .build());
    }

    /** 카테고리별 주변 가맹점 검색 API **/
    @GetMapping("/map/category")
    public ResponseEntity<ResponseDTO> searchShopListByCategory(@RequestParam("category") @NotBlank String category,
                                                             @RequestParam("latitude") @Positive double latitude,
                                                             @RequestParam("longitude") @Positive double longitude,
                                                                @AuthenticationPrincipal UserAuthentication userAuthentication){

        List<ShopSearchResponse> dto= shopUseCase.searchShopListByCategory(category, latitude, longitude, userAuthentication.getId());
        HashMap<String,Object> response= new HashMap<>();
        response.put("shopList", dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("카테고리별 조회")
                .data(response)
                .build());
    }

    /** 경로 추천 API **/
    @PostMapping("/map/rec")
    public ResponseEntity<ResponseDTO> recommandShopList(@Valid @RequestBody ShopRecommandRequest shopRecommandRequest,
                                                             @AuthenticationPrincipal UserAuthentication userAuthentication){

        List<ShopRecommandResponse> dto= shopUseCase.recommandShopList(shopRecommandRequest, userAuthentication.getId());
        HashMap<String,Object> response= new HashMap<>();
        response.put("shopList", dto);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseDTO.builder()
                .message("경로 추천")
                .data(response)
                .build());
    }

}
