package com.test.controller;

//import com.test.redis.ReactiveRedisService;
import com.test.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "RedisController 测试")

@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class RedisController {
    Logger logger = LoggerFactory.getLogger(getClass());
    private final RedisService redisService;

    //private final ReactiveRedisService reactiveRedisService;

    @ApiOperation(value = "/test-redis")
    @GetMapping("/test-redis")
    public void testRedis() {
        redisService.publish();

    }

    /*@GetMapping("/test-redis-reactive")
    public void testRedisReactive() {
        reactiveRedisService.publish();
    }*/

    @ApiOperation(value = "/test-redis-set")
    @GetMapping("/test-redis-set")
    public void testRedisSet() {
        redisService.testSet();

    }

    @ApiOperation(value = "/test-redis-set-add")
    @GetMapping("/test-redis-set-add")
    public void testRedisSetAdd(@RequestParam String name) {
        redisService.testAdd(name);

    }

    @ApiOperation(value = "/test-redis-set-remove")
    @GetMapping("/test-redis-set-remove")
    public void testRedisSetRemove(@RequestParam String name) {
        redisService.testRemove(name);
    }

    @ApiOperation(value = "/test-redis-set-get")
    @GetMapping("/test-redis-set-get")
    public List<String> testRedisSetGet() {
        return redisService.testSetGet();
    }

    @ApiOperation(value = "/test-redis-set-has")
    @GetMapping("/test-redis-set-has")
    public Boolean testRedisSetHas() {
        return redisService.testHashKey();
    }

}
