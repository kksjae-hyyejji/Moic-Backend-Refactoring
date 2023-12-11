package com.finp.moic.util.database.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SecurityRedisConfig {

    @Value("${spring.redis2.host}")
    private String REDIS2_HOST;

    @Value("${spring.redis2.port}")
    private int REDIS2_PORT;

    @Value("${spring.redis2.password}")
    private String REDIS2_PW;

    @Bean(name = "SecurityRedisFactory")
    public RedisConnectionFactory securityRedisFactory() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.setHostName(REDIS2_HOST); // 두 번째 Redis 서버 호스트
        connectionFactory.setPort(REDIS2_PORT); // 두 번째 Redis 서버 포트
        connectionFactory.setPassword(REDIS2_PW);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }

    @Bean(name = "SecurityRedis")
    public RedisTemplate<String, String> SubRedis(@Qualifier("SecurityRedisFactory") RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
