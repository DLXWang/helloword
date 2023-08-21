package com.test.controller;

import com.test.event.asyn.MyAsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController

@RequiredArgsConstructor
@Slf4j
@Api(tags = "AsyncController 测试")
public class AsyncController {
    private final MyAsyncService asyncService;


    @ApiOperation(value = "executeAsync")
    @GetMapping("/executeAsync")
    public CompletableFuture<String> executeAsync(@RequestHeader(value = "tenantId", defaultValue = "1") Long tenantId) {
        log.info("<线程 ：{},tenantId is {}>", Thread.currentThread().getName(), tenantId);
        return asyncService.asyncMethod();
    }
}
