package com.test.api;

import com.test.data.A;
import com.test.data.B;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.FallbackFactory;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "remote-service", url = "http://localhost:8090")
@Repository// 没用，只是为了引用正常，通过@EnableReactiveFeignClients wrap 和 注入
public interface MyApi extends ServiceApi{
    @Override
    @GetMapping("/test/A")
    Mono<A> testA();

    @Override
    @GetMapping("/test/B")
    Mono<B> testB();

    // 没用上，加在ReactiveFeignClient注解里
    @Component
    class MyApiFallbackFactory implements FallbackFactory<MyApi> {

        @Override
        public MyApi apply(Throwable throwable) {
            return new MyApi() {
                @Override
                public Mono<A> testA() {
                    return Mono.empty();
                }

                @Override
                public Mono<B> testB() {
                    return null;
                }
            };
        }
    }
}
