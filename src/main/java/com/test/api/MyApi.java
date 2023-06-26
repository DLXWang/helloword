package com.test.api;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.FallbackFactory;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "remote-service", url = "http://localhost:8090")
@Repository
public interface MyApi extends ServiceApi{
    @Override
    @GetMapping("/test/my")
    Mono<String> testForGet();




    @Component
    class MyApiFallbackFactory implements FallbackFactory<MyApi> {

        @Override
        public MyApi apply(Throwable throwable) {
            return new MyApi() {
                @Override
                public Mono<String> testForGet() {
                    return Mono.empty();
                }
            };
        }
    }
}
