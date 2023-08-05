//package com.test.api;
//
//import com.xt.jwt.constants.SignatureHeader;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//@FeignClient(name = "api-proxy", url = "http://127.0.0.1:8090")
//public interface CommonApi {
//    @GetMapping("/test/common")
//    String testCommon();
//
//    @GetMapping("/hello")
//    String hello(@RequestHeader(SignatureHeader.ACCESS_TOKEN_HEADER) String accessToken
//            ,@RequestHeader(SignatureHeader.REFRESH_TOKEN_HEADER) String refreshToken);
//}
//
