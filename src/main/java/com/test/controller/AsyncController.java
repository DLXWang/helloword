package com.test.controller;

import com.test.event.asyn.MyAsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController

@RequiredArgsConstructor
@Slf4j
public class AsyncController {
    private final MyAsyncService asyncService;



    @GetMapping("/executeAsync")
    public CompletableFuture<String> executeAsync() {
        log.info("<线程 ：{}>",Thread.currentThread().getName());
        return asyncService.asyncMethod();
    }
}
