package com.finp.moic.util.database.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finp.moic.card.model.dto.response.CardMineResponseDTO;
import com.finp.moic.card.model.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    /* 성재 : Redis 서버 3개 분리 */

    private final RedisTemplate<String, Object> mainRedis;
    private final RedisTemplate<String, String> securityRedis;
    private final RedisTemplate<String, String> autoRedis;

    @Autowired
    public RedisService(@Qualifier("MainRedis") RedisTemplate<String, Object> mainRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis,
                        @Qualifier("AutoRedis") RedisTemplate<String, String> autoRedis) {
        this.mainRedis = mainRedis;
        this.securityRedis = securityRedis;
        this.autoRedis = autoRedis;
    }

    /***** [Auto Complete] *****/

    /* 성재 : Auto Complete 작성 부분 남겨두기 */

    /***** [Refresh Token] *****/
    public void setRefreshToken(String userId, String refreshToken){
        // key : refresh, value : userId
        securityRedis.opsForValue().set(userId,refreshToken);
        //30일
        securityRedis.expire(userId,30L, TimeUnit.DAYS);
    }

    public String getRefreshToken(String userId){
        return securityRedis.opsForValue().get(userId);
    }

    public boolean deleteRefreshToken(String userId){
        return securityRedis.delete(userId);
    }

    public void setCertNumber(String userId, String certNumber){
        // key : userId, value : certNumber
        securityRedis.opsForValue().set(userId, certNumber);
        //3분
        securityRedis.expire(userId,180L, TimeUnit.SECONDS);
    }

    public String getCertNumber(String userId){
        return securityRedis.opsForValue().get(userId);
    }

    public void setCertTime(String userId){
        securityRedis.expire(userId,180L, TimeUnit.SECONDS);
    }

}
