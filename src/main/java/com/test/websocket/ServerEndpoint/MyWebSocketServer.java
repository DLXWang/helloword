/*
package com.test.websocket.ServerEndpoint;

import org.yeauty.annotation.OnClose;
import org.yeauty.annotation.OnMessage;
import org.yeauty.annotation.OnOpen;
import org.yeauty.annotation.ServerEndpoint;
import org.yeauty.pojo.Session;



@ServerEndpoint("/end-point-websocket")
public class MyWebSocketServer {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket Connection Opened: " + session.channel().id().toString());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received Message: " + message);
        try {
            session.sendText("Received: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket Connection Closed: " + session.channel().id().toString() );
    }


}

*/
