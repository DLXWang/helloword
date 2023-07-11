package com.test.controller;

import com.test.redis.RedisService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequiredArgsConstructor
public class RedisController {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final RedisService redisService;


    @GetMapping("/test-redis")
    public void testRedis() {
        redisService.publish();

    }

    @GetMapping("/test-redis-set")
    public void testRedisSet() {
        redisService.testSet();

    }

    @GetMapping("/test-redis-set-add")
    public void testRedisSetAdd(@RequestParam String name) {
        redisService.testAdd(name);

    }

    @GetMapping("/test-redis-set-remove")
    public void testRedisSetRemove(@RequestParam String name) {
        redisService.testRemove(name);
    }

    @GetMapping("/test-redis-set-get")
    public List<String> testRedisSetGet() {
        return redisService.testSetGet();
    }

    @GetMapping("/test-redis-set-has")
    public Boolean testRedisSetHas() {
        return redisService.testHashKey();
    }

}
