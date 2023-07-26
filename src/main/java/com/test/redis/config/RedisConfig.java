package com.test.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.redis.HedgeAccount;
import com.test.redis.HedgeUserFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnProperty(name = "myapp.feature.enabled", havingValue = "true", matchIfMissing = false)
    public ReactiveRedisTemplate<String, HedgeUserFilter> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<HedgeUserFilter> valueSerializer = new Jackson2JsonRedisSerializer<>(HedgeUserFilter.class);

        RedisSerializationContext<String, HedgeUserFilter> serializationContext = RedisSerializationContext.<String, HedgeUserFilter>newSerializationContext()
                .key(keySerializer)
                .value(valueSerializer)
                .hashKey(keySerializer)
                .hashValue(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }

    @Bean
    @ConditionalOnProperty(name = "myapp.feature.enabled", havingValue = "true", matchIfMissing = false)
    public ReactiveRedisTemplate<String, HedgeAccount> reactiveRedisTemplate4HedgeAccount(ReactiveRedisConnectionFactory factory) {
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        Jackson2JsonRedisSerializer<HedgeAccount> valueSerializer = new Jackson2JsonRedisSerializer<>(HedgeAccount.class);
        valueSerializer.setObjectMapper(objectMapper);

        RedisSerializationContext<String, HedgeAccount> serializationContext = RedisSerializationContext.<String, HedgeAccount>newSerializationContext()
                .key(keySerializer)
                .value(valueSerializer)
                .hashKey(keySerializer)
                .hashValue(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, serializationContext);
    }
}

