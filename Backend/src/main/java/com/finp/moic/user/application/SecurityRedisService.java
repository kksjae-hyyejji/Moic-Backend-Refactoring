package com.finp.moic.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class SecurityRedisService {

    private final RedisTemplate<String, String> securityRedis;

    @Autowired
    public SecurityRedisService(@Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis) {
        this.securityRedis = securityRedis;
    }

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
