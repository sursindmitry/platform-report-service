package com.sursindmitry.reportservice.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Конфигурация кеширования Redis.
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    /**
     * Конфигурация Redis.
     *
     * <p>Параметры:</p>
     * <ul>
     *   <li><b>redisConnectionFactory</b> – фабрика Redis</li>
     * </ul>
     *
     * <p>Возвращает:</p>
     * <ul>
     *   {@link CacheManager}
     * </ul>
     *
     * <p>Кэши и время их хранения:</p>
     * <ul>
     *   <li><b>userCache</b> – 10 минут</li>
     *   <li><b>minuteCache</b> – 1 минута</li>
     *   <li><b>shortLivedCache</b> – 10 секунд</li>
     * </ul>
     */


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer()))
            .disableCachingNullValues();

        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        cacheConfigurationMap.put(
            "userCache",
            defaultConfig.entryTtl(Duration.ofMinutes(10))
        );
        cacheConfigurationMap.put(
            "minuteCache",
            defaultConfig.entryTtl(Duration.ofMinutes(1))
        );
        cacheConfigurationMap.put(
            "shortLivedCache",
            defaultConfig.entryTtl(Duration.ofSeconds(10))
        );

        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(defaultConfig)
            .withInitialCacheConfigurations(cacheConfigurationMap)
            .build();
    }
}
