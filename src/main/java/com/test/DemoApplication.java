package com.test;

import com.test.api.CommonApi;
import com.test.api.MyApi;
import com.test.data.A;
import com.test.data.B;
import com.test.data.C;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;

@SpringBootApplication
@RestController
@MapperScan(basePackages = {"com.test.mysql.mapper"})
@EnableAsync
public class DemoApplication {
    Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        logger.info("< 收到的name 是 {}>", name);
        return String.format("Hello %s!", name);
    }

    @GetMapping("/test/common")
    public String testCommon() {
        return "common-test";
    }

    @GetMapping("/test/A")
    public Mono<A> test() {
        return Mono.just(A.builder().name("ddd").aps(Collections.singletonList(A.AP.builder().apName("app").build())).build());
    }

    @GetMapping("/test/B")
    public Mono<B> testB() {
        return Mono.just(B.builder().num(12L).build());
    }


    @Autowired
    private MyApi myApi;
    @Autowired
    private CommonApi commonApi;

    @GetMapping("/test/invoke")
    public Mono<C> invoke() {
        Mono<String> just = Mono.just(commonApi.testCommon());
        return Mono.zip(myApi.testA(), myApi.testB(), just).map(it -> {
            C build = C.builder().build();
            A t1 = it.getT1();
            t1.setName(it.getT3());
            build.setA(t1);
            build.setB(it.getT2());
            return build;
        });
    }
}
