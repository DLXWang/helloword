package com.test;

import com.test.api.MyApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController

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

    @GetMapping("/test/my")
    public Mono<String> test() {
        return Mono.just("test");
    }


    @Autowired
    private MyApi myApi;

    @GetMapping("/test/invoke")
    public Mono<String> invoke() {
        return  myApi.testForGet().map(resource -> "remote result is : " + resource);
    }
}
