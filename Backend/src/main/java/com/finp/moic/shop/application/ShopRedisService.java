package com.finp.moic.shop.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.shop.application.port.in.ShopRedisUseCase;
import com.finp.moic.shop.application.response.ShopRecommandResponse;
import com.finp.moic.shop.application.response.ShopSearchResponse;
import com.finp.moic.shop.domain.Shop;
import com.finp.moic.shop.application.response.ShopGeoRedisReponse;
import com.finp.moic.util.exception.ExceptionEnum;
import com.finp.moic.util.exception.list.DeniedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ShopRedisService implements ShopRedisUseCase {

    private final RedisTemplate<String, Object> mainRedis;
    private final GeoOperations<String, Object> geoOperations;

    @Autowired
    public ShopRedisService(@Qualifier("MainRedis") RedisTemplate<String, Object> mainRedis) {
        this.mainRedis = mainRedis;
        this.geoOperations = mainRedis.opsForGeo();
    }

    /**
     * 가맹점별 위치와 정보 저장 (Redis가 날아갔을 경우 대비 -> 되도록 쓰지 말기)
     **/
    @Override
    public void setGeoShopList(List<Shop> shopList) {
        /** Redis Access **/
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            for (Shop shop : shopList) {
                geoOperations.add(
                        shop.getName(), //KEY
                        new Point(shop.getLongitude(), shop.getLatitude()), //POINT
                        objectMapper.writeValueAsString( //MEMBER
                                ShopGeoRedisReponse
                                        .builder()
                                        .mainCategory(shop.getMainCategory())
                                        .category(shop.getCategory())
                                        .location(shop.getLocation())
                                        .address(shop.getAddress())
                                        .guName(shop.getGuName())
                                        /* 혜지 Redis의 Point는 소수점 아래 여섯자리까지 저장한다. 따라서 우리의 데이터 이용 */
                                        .latitude(shop.getLatitude())
                                        .longitude(shop.getLongitude())
                                        .build()
                        )
                );
                /* 혜지 : Shop에 대한 TTL -1 설정 */
                mainRedis.expire(shop.getName(), -1L, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            throw new DeniedException(ExceptionEnum.SHOP_SAVE_ERROR);
        }
    }

    @Override
    public List<ShopSearchResponse> searchShopListNearByUser(String shopName, double latitude, double longitude) {

        List<ShopSearchResponse> dto = new ArrayList<>();

        /** Redis Access **/
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = geoOperations.radius(shopName,
                new Circle(new Point(longitude, latitude), new Distance(1, RedisGeoCommands.DistanceUnit.KILOMETERS)));

        /** DTO Builder **/
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> geoResult : results) {
            RedisGeoCommands.GeoLocation<Object> location = geoResult.getContent();
            String json = (String) location.getName();

            try {
                ShopGeoRedisReponse redisDTO = ShopGeoRedisReponse.fromJson(json);
                ShopSearchResponse searchDTO = ShopSearchResponse.builder()
                        .category(redisDTO.getCategory())
                        .shopName(shopName)
                        .shopLocation(redisDTO.getLocation())
                        .address(redisDTO.getAddress())
                        .latitude(redisDTO.getLatitude())
                        .longitude(redisDTO.getLongitude())
                        .build();
                dto.add(searchDTO);
            } catch (Exception e) {
                throw new DeniedException(ExceptionEnum.SHOP_SEARCH_ERROR);
            }
        }

        return dto;
    }

    @Override
    public ShopRecommandResponse searchShopNearByUser(String shopName, double latitude, double longitude) {

        /** Redis Access **/
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = geoOperations.radius(shopName,
                new Circle(new Point(longitude, latitude), new Distance(1, RedisGeoCommands.DistanceUnit.KILOMETERS)));

        /** DTO Builder **/
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> geoResult : results) {
            RedisGeoCommands.GeoLocation<Object> location = geoResult.getContent();
            String json = (String) location.getName();

            try {
                ShopGeoRedisReponse redisDTO = ShopGeoRedisReponse.fromJson(json);
                ShopRecommandResponse dto = ShopRecommandResponse.builder()
                        .shopName(shopName)
                        .shopLocation(redisDTO.getLocation())
                        .address(redisDTO.getAddress())
                        .x(redisDTO.getLongitude())
                        .y(redisDTO.getLatitude())
                        .build();

                return dto;
            } catch (Exception e) {
                throw new DeniedException(ExceptionEnum.SHOP_SEARCH_ERROR);
            }
        }
        /* 혜지 : 반경 1km 내에 해당 shop이 없는 경우 전체 조회 */
        return searchShop(shopName, latitude, longitude);
    }

    @Override
    public ShopRecommandResponse searchShop(String shopName, double latitude, double longitude) {

        /** Redis Access **/
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = geoOperations.radius(shopName,
                new Circle(new Point(longitude, latitude), new Distance(25, RedisGeoCommands.DistanceUnit.KILOMETERS)));

        /** DTO Builder **/
        for (GeoResult<RedisGeoCommands.GeoLocation<Object>> geoResult : results) {
            RedisGeoCommands.GeoLocation<Object> location = geoResult.getContent();
            String json = (String) location.getName();

            try {
                ShopGeoRedisReponse redisDTO = ShopGeoRedisReponse.fromJson(json);
                ShopRecommandResponse dto = ShopRecommandResponse.builder()
                        .shopName(shopName)
                        .shopLocation(redisDTO.getLocation())
                        .address(redisDTO.getAddress())
                        .x(redisDTO.getLongitude())
                        .y(redisDTO.getLatitude())
                        .build();

                return dto;
            } catch (Exception e) {
                throw new DeniedException(ExceptionEnum.SHOP_SEARCH_ERROR);
            }
        }
        return null;
    }
}
