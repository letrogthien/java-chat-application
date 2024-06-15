package com.winform;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.google.gson.Gson;
import com.winform.config.session.SessionManager;
import com.winform.models.Message;
import java.lang.reflect.Type;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author agin0
 */
public class SocketClient  {
    
    private SessionManager sessionManager;
    private StompSession stompSession;

    public SocketClient() {
       
    }
    public void sendPrivateMessage(Message message) {
        if (stompSession != null) {
            stompSession.send("/app/private-message", message);
            System.out.println("Private message sent successfully: " + message);
        } else {
            System.err.println("StompSession is null. Cannot send message.");
        }
    }
    
    public void connectSocket() {
        org.springframework.web.socket.client.WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        CountDownLatch latch = new CountDownLatch(1); // Tạo CountDownLatch

        StompSessionHandlerAdapter sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                latch.countDown(); // Giảm CountDownLatch khi kết nối thành công
                System.out.println("connect ok");
                session.subscribe("/user/" + sessionManager.getUserId().toString() + "/private", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return Message.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        Message message = (Message) payload;
                        System.out.println("Received message: " + message);
                        System.out.println("Message received successfully: " + message);
                    }
                   
                });
                System.out.println(session);
            }
        };

        stompClient.connect("ws://localhost:8080/ws/websocket", sessionHandler);

        // Chờ cho đến khi kết nối hoàn thành hoặc timeout
        try {
            if (!latch.await(10, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Connection timed out");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException("Connection interrupted", e);
        }
    }
    
    
}
