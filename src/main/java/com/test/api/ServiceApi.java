package com.test.api;

import com.test.data.A;
import com.test.data.B;
import reactor.core.publisher.Mono;

public interface ServiceApi {
    Mono<A> testA();

    Mono<B> testB();
}
