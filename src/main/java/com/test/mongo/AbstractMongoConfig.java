package com.test.mongo;

import com.mongodb.ConnectionString;
import lombok.Data;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;


/**
 * @author wangxinxing
 */
@Data
public abstract class AbstractMongoConfig {
    private String uri;

    public abstract ReactiveMongoTemplate mongoTemplate();

    public ReactiveMongoDatabaseFactory mongoDbFactory() {
        return new SimpleReactiveMongoDatabaseFactory(new ConnectionString(uri));
    }
}

