package com.test.websocket;

import org.springframework.web.socket.*;

public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 在连接建立后执行的逻辑
        System.out.println("WebSocket连接已建立");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 处理接收到的消息
        String payload = message.getPayload().toString();
        System.out.println("接收到消息：" + payload);

        // 发送回复消息
        session.sendMessage(new TextMessage("收到消息：" + payload));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 处理传输错误
        System.out.println("WebSocket传输错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 在连接关闭后执行的逻辑
        System.out.println("WebSocket连接已关闭");

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

