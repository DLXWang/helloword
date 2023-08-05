package com.test.controller;


import com.test.websocket.demo_register.client.WebSocketClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "WebSocketController 测试")

@RequiredArgsConstructor
public class WebSocketController {

    private final WebSocketClientService webSocketClientService;

    @ApiOperation(value = "test-ws-client")
    @GetMapping("/test-ws-client")
    public void testEventPublish( ) {
        webSocketClientService.sendMsg();
    }

}
