package com.test.websocket.ServerEndpoint;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;



@ServerEndpoint(path = "/ws/order", port = "8080") // 不需要其他，随web容器，web容器会负责注入
@Slf4j
@RequiredArgsConstructor
public class NettyOrderWebSocket {


    private final Gson gson = new Gson();

    @BeforeHandshake
    public void handshake(Session session) {
    }

    @OnOpen
    public void onOpen(Session session) {
        log.info("connect-on=<{}>", session);

    }

    @OnClose
    public void onClose(Session session) {
        log.info("connect-off=<{}>", session);

    }

    @OnError
    public void onError(Session session, Throwable throwable) {

        log.error("异常处理，,e=<{}>,e=<{}>,", throwable.getMessage(), throwable, throwable);
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        if (StringUtils.equalsIgnoreCase(message, "ping")) {
            session.sendText("pong");
        }
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
    }
}
