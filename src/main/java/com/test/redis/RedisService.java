package com.test.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class RedisService {
    private final ReactiveStringRedisTemplate reactiveRedisTemplate;
    Random random = new Random();


    @PostConstruct
    public void init() {
        reactiveRedisTemplate.listenToChannel("testRedisTopic").subscribe(message -> {
                    long l = Long.parseLong(message.getMessage().trim());
                    System.out.println("< 收到的 为>" + l);


                }
        );
    }

    public void publish() {
        reactiveRedisTemplate.convertAndSend("testRedisTopic", random.nextInt(10) + "").subscribe();
    }

}
