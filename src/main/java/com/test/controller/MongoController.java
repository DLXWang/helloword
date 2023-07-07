package com.test.controller;


import com.test.mongo.HedgeAccount;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class MongoController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Qualifier("testMongoTemplate")
    private final ReactiveMongoTemplate reactiveMongoTemplate;


    @GetMapping("/test-mongo-save")
    public void testSave(@RequestParam Long id, @RequestParam String name) {
        HedgeAccount build = HedgeAccount.builder()
                .id(id)
                .name(name)
                .createdTime(System.currentTimeMillis())
                .updatedTime(System.currentTimeMillis())
                .build();
        reactiveMongoTemplate.save(build).subscribe();
    }

    @GetMapping("/test-mongo-remove")
    public void testRemove(@RequestParam Long id) {
        HedgeAccount build = HedgeAccount.builder()
                .id(id)
                .build();
        reactiveMongoTemplate.remove(build).subscribe();
    }
}
