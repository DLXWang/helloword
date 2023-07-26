/*
package com.test.redis;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ReactiveRedisService {
    private final ReactiveRedisTemplate<String, HedgeUserFilter> reactiveRedisTemplate;
    private final ReactiveRedisTemplate<String, HedgeAccount> reactiveRedisTemplate1;


    @PostConstruct
    public void init() {

        reactiveRedisTemplate.listenToChannel("testRedisTopic").subscribe(message -> {
                    HedgeUserFilter message1 = message.getMessage();
                    System.out.println(message1);
                }
        );
        reactiveRedisTemplate1.listenToChannel("testRedisTopic-02").subscribe(message -> {
            HedgeAccount message1 = message.getMessage();
            System.out.println(message1);
                }
        );
    }

    public void publish() {
        reactiveRedisTemplate.convertAndSend("testRedisTopic",  HedgeUserFilter.builder().accountId(1L)
                .autoEnabled(false)
                .symbolExclude(Lists.newArrayList(1L,2L))
                .role("dd")
                .build()).subscribe();

        reactiveRedisTemplate1.convertAndSend("testRedisTopic-02", HedgeAccount.builder()
                .comment("dd")
                .updatedTime(new Date())
                .createdTime(LocalDateTime.now())
                .enabled(false)
                .id(12L)
                .build()).subscribe();
    }

}
*/
