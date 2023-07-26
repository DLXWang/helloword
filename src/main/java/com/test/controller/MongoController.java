package com.test.controller;


import com.test.mongo.HedgeAccount;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RestController

@RequiredArgsConstructor
public class MongoController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Qualifier("testMongoTemplate")
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final MongoTemplate mongoTemplate;



    @GetMapping("/test-mongo-save")
    public void testSave(@RequestParam Long id, @RequestParam String name) {
        HedgeAccount build = HedgeAccount.builder()
                .id(id)
                .name(name)
                .comment("1")
                .enabled(true)
                .creatorId("eden")
                .updaterId("eden")
                .createdTime(System.currentTimeMillis())
                .updatedTime(System.currentTimeMillis())
                .build();
        try {
            HedgeAccount insert = mongoTemplate.save(build);
            System.out.println(insert);
        }catch (DuplicateKeyException exception){
            System.out.println();
        }

    }

    @GetMapping("/test-mongo-remove")
    public void testRemove(@RequestParam Long id) {
        HedgeAccount build = HedgeAccount.builder()
                .id(id)
                .build();
        reactiveMongoTemplate.remove(build).subscribe();
    }

    @GetMapping("/test-mongo-update")
    public void testUpdate(@RequestParam Long id) {
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        HedgeAccount andModify = mongoTemplate.findAndModify(Query.query(Criteria.where("_id").is(id)),
                new Update().set("comment", "update1"), options,HedgeAccount.class);
        System.out.println(andModify);
    }

    @GetMapping("/test-mongo-agg")
    public void testAgg() {
        Criteria criteria = Criteria.where("_id").gt(18);
        GroupOperation groupOperation = group("name").first("name").as("name");
        ProjectionOperation projectionOperation = project().andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria), groupOperation,projectionOperation);
        List<HedgeAccount> block = reactiveMongoTemplate.aggregate(aggregation, HedgeAccount.class, HedgeAccount.class).collectList().block();
        System.out.println(block);
    }
}
