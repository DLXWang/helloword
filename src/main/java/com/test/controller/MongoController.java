package com.test.controller;


import com.test.controller.model.ResultType;
import com.test.mongo.DailyFee;
import com.test.mongo.HedgeAccount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RestController

@RequiredArgsConstructor
@Api(tags = "MongoController 测试")

public class MongoController {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Qualifier("testMongoTemplate")
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private final MongoTemplate mongoTemplate;


    @ApiOperation(value = "test-mongo-save")
    @GetMapping("/test-mongo-save")
    public void testSave(@RequestParam BigDecimal fee, @RequestParam String name) {
        DailyFee binance = DailyFee.builder()
                .dayTime(LocalDateTime.now(ZoneId.of("UTC+8")))
                .exchangeId("BINANCE")
                .symbol(name)
                .totalFee(fee)
                .selectedAccountId(1L).build();
        mongoTemplate.save(binance);
    }

    @ApiOperation(value = "test-mongo-query")
    @GetMapping("/test-mongo-query")
    public List<DailyFee> testQuery() {
        /*Query query = new Query();
        query.addCriteria(Criteria.where("symbol").is("ETH"));
        query.addCriteria(Criteria.where("selectedAccountId").is(1));*/
        /*Criteria criteria = Criteria.where("symbol").is("ETH");
        criteria.and("selectedAccountId").is(1);
        return mongoTemplate.find(Query.query(criteria),DailyFee.class);*/
        Query query = Query.query(Criteria.where("totalFee").gt(BigDecimal.ZERO).and("symbol").is("BTC"));
        query.fields().include("totalFee", "symbol");
        return mongoTemplate.find(query, DailyFee.class);

    }


    @ApiOperation(value = "test-mongo-remove")
    @GetMapping("/test-mongo-remove")
    public void testRemove(@RequestParam Long id) {
        HedgeAccount build = HedgeAccount.builder()
                .id(id)
                .build();
        reactiveMongoTemplate.remove(build).subscribe();
    }


    @ApiOperation(value = "test-mongo-update")
    @GetMapping("/test-mongo-update")
    public void testUpdate(@RequestParam Long id) {
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        HedgeAccount andModify = mongoTemplate.findAndModify(Query.query(Criteria.where("_id").is(id)),
                new Update().set("comment", "update1"), options, HedgeAccount.class);
        System.out.println(andModify);
    }

    @ApiOperation(value = "test-mongo-agg")
    @GetMapping("/test-mongo-agg")
    public void testAgg() {
        Criteria criteria = Criteria.where("_id").gt(18);
        GroupOperation groupOperation = group("name").first("name").as("name");
        ProjectionOperation projectionOperation = project().andExclude("_id");
        Aggregation aggregation = newAggregation(Aggregation.match(criteria), groupOperation, projectionOperation);
        List<HedgeAccount> block = reactiveMongoTemplate.aggregate(aggregation, HedgeAccount.class, HedgeAccount.class).collectList().block();
        System.out.println(block);
    }

    @ApiOperation(value = "test-mongo-agg-sum")
    @GetMapping("/test-mongo-agg-sum")
    public List<ResultType> testAggSum() {
        Criteria criteria = Criteria.where("exchangeId").is("BINANCE");

        Aggregation aggregation = newAggregation(
                Aggregation.match(criteria),
                project("totalFee", "selectedAccountId", "symbol")
                        .and(ConvertOperators.ToDouble.toDouble("$totalFee")).as("totalFee")
                        .and(ConvertOperators.ToDouble.toDouble("$selectedAccountId")).as("selectedAccountId"),
                project("totalFee", "selectedAccountId", "symbol").and("totalFee").multiply("selectedAccountId").as("totalFeeTimesVolume"),

                group("symbol").sum("totalFeeTimesVolume").as("result1")
                        .sum("selectedAccountId").as("result2")
                        .first("symbol").as("symbol")


        );

        Flux<ResultType> aggregate = reactiveMongoTemplate.aggregate(
                aggregation, DailyFee.class, ResultType.class
        );

        List<ResultType> mappedResults = aggregate.collectList().block();
        System.out.println(mappedResults);
        return mappedResults;

    }


    @ApiOperation(value = "test-mongo-agg-reduce")
    @GetMapping("/test-mongo-agg-reduce")
    public ResultType testAggReduce() {
        Criteria criteria = Criteria.where("exchangeId").is("BINANCE");

        Aggregation aggregation = newAggregation(
                Aggregation.match(criteria),
                project("totalFee").and(ConvertOperators.ToDouble.toDouble("$totalFee")).as("totalFee"),
                group().sum("totalFee").as("result1")
        );

        Flux<ResultType> aggregate = reactiveMongoTemplate.aggregate(
                aggregation, DailyFee.class, ResultType.class
        );

         ResultType mappedResult = aggregate.blockFirst();
        System.out.println(mappedResult);
        return mappedResult;
    }
}
