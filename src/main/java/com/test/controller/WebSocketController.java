package com.test.controller;


import com.test.websocket.client.WebSocketClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequiredArgsConstructor
public class WebSocketController {

    private final WebSocketClientService webSocketClientService;

    @GetMapping("/test-ws-client")
    public void testEventPublish( ) {
        webSocketClientService.sendMsg();
    }

}
