package com.test.api;

import reactor.core.publisher.Mono;

public interface ServiceApi {
    Mono<String> testForGet();
}
