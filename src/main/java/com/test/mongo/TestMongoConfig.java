package com.test.mongo;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;


/**
 * @description: thanos mongoDb 配置
 * @author: wangxinxing
 * @date: 2023/6/9
 */

@Configuration
@ConditionalOnProperty(prefix = "spring.data.mongodb.test", name = "uri")
@ConfigurationProperties(prefix = "spring.data.mongodb.test")
public class TestMongoConfig extends AbstractMongoConfig {

    @Override
    @Bean(name = "testMongoTemplate")
    public ReactiveMongoTemplate mongoTemplate() {
        return new ReactiveMongoTemplate(mongoDbFactory());
    }

    @Bean("testMongoTransactionManager")
    ReactiveMongoTransactionManager mongoTransactionManager() {
        return new ReactiveMongoTransactionManager(mongoDbFactory());
    }

}


