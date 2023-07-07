package com.test.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "api-proxy",url = "http://127.0.0.1:8090")
public interface CommonApi {
    @GetMapping("/test/common")
    String testCommon();
}
