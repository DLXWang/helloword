package com.test.event.asyn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MyAsyncService {
    @Async
    public CompletableFuture<String> asyncMethod() {
        // 异步方法的逻辑
       log.info("<线程 ：{}>",Thread.currentThread().getName());
        return CompletableFuture.completedFuture("异步方法执行完毕");
    }
}

