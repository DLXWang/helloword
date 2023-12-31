package com.test.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class RedisService {
    private final ReactiveStringRedisTemplate reactiveRedisTemplate;
    Random random = new Random();

    @Value("${symbol.from-redis}")
    private boolean fromRedis;

    @PostConstruct
    public void init() {
        System.out.println(fromRedis);
        reactiveRedisTemplate.listenToChannel("testRedisTopic_01").subscribe(message -> {
                    long l = Long.parseLong(message.getMessage().trim());
                    System.out.println("< 收到的 为>" + l);


                }
        );
    }

    public void publish() {
        reactiveRedisTemplate.convertAndSend("testRedisTopic", random.nextInt(10) + "").subscribe();
    }

    public void testSet() {
        List<String> values = List.of("BTC", "ETH");
        reactiveRedisTemplate.opsForSet().add("COIN", values.toArray(new String[0])).subscribe();
    }

    public void testAdd(String name) {
        reactiveRedisTemplate.opsForSet().add("COIN", name).subscribe();
    }

    public void testRemove(String name) {
        reactiveRedisTemplate.opsForSet().remove("COIN", name).subscribe();
    }

    public List<String> testSetGet() {
        return reactiveRedisTemplate.opsForSet().members("COIN").collectList().block();
    }

    public Boolean testHashKey() {
        return reactiveRedisTemplate.hasKey("COIN").block();
    }

}
