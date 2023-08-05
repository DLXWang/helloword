package com.test.websocket.demo_register.client;




import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
@Service
public class WebSocketClientService {

    public void sendMsg(){
        // 创建 WebSocket 客户端
        ReactorNettyWebSocketClient webSocketClient = new ReactorNettyWebSocketClient();

        // 定义 WebSocket 服务器的 URL
        URI serverUri = URI.create("ws://localhost:8090/my-websocket");

        // 创建 WebSocket 处理器
        WebSocketHandler webSocketHandler = session -> {
            // 连接建立后发送消息
            Mono<Void> sendMessage = session.send(Mono.just(session.textMessage("Hello, WebSocket!")));

            // 处理接收到的消息
            Mono<Void> handleMessage = session.receive()
                    .doOnNext(message -> {
                        String payload = message.getPayloadAsText();
                        System.out.println("接收到消息：" + payload);
                        // 处理接收到的消息
                    })
                    .doOnError(error -> {
                        // 错误处理逻辑
                        System.err.println("接收消息时发生错误：" + error.getMessage());
                    })
                    .then();

            // 返回发送消息和处理消息的组合
            return Mono.zip(sendMessage, handleMessage).then();
        };

        // 建立 WebSocket 连接并执行处理逻辑
        webSocketClient.execute(serverUri, webSocketHandler).block();
        System.out.println();

        // 程序执行完成后，可执行其他操作或关闭应用程序

    }
}
