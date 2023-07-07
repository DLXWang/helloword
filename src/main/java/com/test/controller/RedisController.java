package com.test.controller;

import com.test.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class RedisController {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final RedisService redisService;


    @GetMapping("/test-redis")
    public void testRedis() {
        redisService.publish();

    }
}
