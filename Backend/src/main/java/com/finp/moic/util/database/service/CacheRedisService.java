package com.finp.moic.util.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CacheRedisService {

    private final RedisTemplate<String, Object> mainRedis;
    private final SetOperations<String,Object> setOperations;

    @Autowired
    public CacheRedisService(@Qualifier("MainRedis") RedisTemplate<String, Object> mainRedis) {
        this.mainRedis = mainRedis;
        this.setOperations = mainRedis.opsForSet();
    }

    /*** Key Generater (private) ***/
    private String setUserCardKey(String userId){ return userId+".card"; }
    private String setUserBenefitShopKey(String userId){return userId+".benefitShop";}
    private String setUserGiftShopKey(String userId){return userId+".giftShop";}

    /*** RemoveAll ***/
    public void removeUserCard(String userId){
        mainRedis.delete(setUserCardKey(userId));
    }

    public void removeUserBenefitShop(String userId){
        mainRedis.delete(setUserBenefitShopKey(userId));
    }

    public void removeUserGiftShop(String userId){
        mainRedis.delete(setUserGiftShopKey(userId));
    }

    /*** exist Key ***/
    public boolean existUserCardKey(String userId) {
        return mainRedis.hasKey(setUserCardKey(userId));
    }

    public boolean existUserBenefitShopKey(String userId) {
        return mainRedis.hasKey(setUserBenefitShopKey(userId));
    }

    public boolean existUserGiftShopKey(String userId) {
        return mainRedis.hasKey(setUserGiftShopKey(userId));
    }

    /*** exist One ***/
    public boolean existUserBenefitShop(String shopName, String userId){
        return setOperations.isMember(setUserBenefitShopKey(userId),shopName);
    }

    public boolean existUserGiftShop(String shopName, String userId){
        return setOperations.isMember(setUserGiftShopKey(userId),shopName);
    }

    /*** Operations ***/
    public void setUserBenefitShopList(List<String> shopNameList,String userId){
        /* 혜지 : 중복 데이터를 제거하기 위해서 부득히 for문 이용 */
        for(String shopName:shopNameList){
            if(!existUserBenefitShop(shopName,userId)&&shopName!=null)
                setOperations.add(setUserBenefitShopKey(userId),shopName);
        }

        /* 혜지 : 1시간 후 만료 설정 */
        mainRedis.expire(setUserBenefitShopKey(userId),3600L,TimeUnit.SECONDS);
    }

    public Set<String> getUserBenefitShopList(String userId){
        Set<Object> redisSet=setOperations.members(setUserBenefitShopKey(userId));

        return redisSet.stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    public void setUserGiftShopList(List<String> shopNameList, String userId){
        /* 혜지 : 중복 데이터를 제거하기 위해서 부득히 for문 이용 */
        for(String shopName:shopNameList){
            if(!existUserGiftShop(shopName,userId)&&shopName!=null)
                setOperations.add(setUserGiftShopKey(userId),shopName);
        }

        /* 혜지 : 1시간 후 만료 설정 */
        mainRedis.expire(setUserGiftShopKey(userId),3600L,TimeUnit.SECONDS);
    }

    public Set<String> getUserGiftShopList(String userId){
        Set<Object> redisSet=setOperations.members(setUserGiftShopKey(userId));

        return redisSet.stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

}
