/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.winform.controllers;

import com.google.gson.Gson;
import com.winform.config.session.SessionManager;
import com.winform.event.EventChat;
import com.winform.event.PublicEvent;
import com.winform.eventListener.ChatEvent;
import com.winform.models.LocalMessage;
import com.winform.models.Message;
import com.winform.models.Messagetype;
import com.winform.models.User;
import com.winform.restapi.ChatClient;
import com.winform.services.UserService;
import com.winform.views.Main;
import com.winform.views.homeViews.Chat;
import com.winform.views.homeViews.Chat_Body;
import com.winform.views.homeViews.Home;
import com.winform.views.homeViews.Menu_Right;
import java.awt.TrayIcon.MessageType;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.client.WebSocketClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketHttpHeaders;

/**
 *
 * @author agin0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeController {

    private PublicEvent publicEvent;
    private Main main;
    private UserService userService;
    private Chat_Body chatBody;
    private SessionManager sessionManager;
    private Menu_Right menuRight;
    private StompSession stompSession;
    private ChatClient chatClient = new ChatClient();
    private List<LocalMessage> localMessages = new ArrayList<>();
    private User targetUser;

    public HomeController(Main main) {
        this.targetUser = new User();
        this.main = main;
        this.userService = new UserService();

        this.chatBody = main.getHome().getChat1().getChat_Body1();
        this.menuRight = main.getHome().getMenu_Right1();
        this.sessionManager = SessionManager.getInstance(null);
        initController();
        System.out.println(sessionManager.getUserId());
    }

    private void initController() {
        connectSocket();
        updateUserList();

        main.getHome().getMenu_Right1().setUserSelectionListener(new ChatEvent() {
            @Override
            public void onUserSelected(User user) {
                targetUser = user;
                if (!checkStoredHaveMessage(user)) {
                    List<Message> messages = getMessageFromServer(user);
                    LocalMessage localMessage = new LocalMessage(messages, user);
                    localMessages.add(localMessage);
                }
                updateChatEvent(user);
                updateChatView(user);
            }
        });
        this.main.getHome().getMenu_Right1().getJButton1().addActionListener(e -> searchAction());

    }

    private List<Message> getMessageFromServer(User user) {
        return chatClient.getMessage(sessionManager.getUserId(), user.getId());
    }

    private boolean checkStoredHaveMessage(User user) {
        if (localMessages != null) {
            for (LocalMessage localMessage : localMessages) {
                if (localMessage.getUser().equals(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateChatEvent(User user) {

        if (user != null) {
            EventChat eventChat = new EventChat() {
                @Override
                public void sendMessage(String text) {
                    Message message = new Message();
                    message.setSenderId(sessionManager.getUserId()); // ID của người gửi
                    message.setRecipientId(user.getId()); // ID của người nhận
                    message.setTimestamp(new Date(System.currentTimeMillis()));
                    message.setContent(text);
                    message.setMessageType(Messagetype.TEXT);
                    message.setReadReceipt(false);
                    message.setChatId("chat123");
                    message.setStatus("sent");
                    sendPrivateMessage(message);
                    main.getHome().getChat1().getChat_Body1().addItemRight(text);
                    addMessage(user.getId(), message);
                }
            };
            main.getHome().getChat1().getChat_Bottom1().chat1(eventChat);
        }
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
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        CountDownLatch latch = new CountDownLatch(1);

        StompSessionHandlerAdapter sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                stompSession = session;
                latch.countDown();
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
                        User user = userService.getUSerById(message.getSenderId());
                        if (user.getId() == targetUser.getId()) {
                            main.getHome().getChat1().getChat_Body1().addItemLeft(message.getContent(), user);
                        }
                        addMessage(user.getId(), message);

                    }

                });
                System.out.println(session);
            }
        };
     
        
        stompClient.connect("ws://localhost:8080/ws/websocket?userId="+sessionManager.getUserId().toString(), sessionHandler);

        // Chờ cho đến khi kết nối hoàn thành hoặc timeout
        try {
            if (!latch.await(10, TimeUnit.SECONDS)) {
                throw new IllegalStateException("Connection timed out");
            }
        } catch (InterruptedException e) {
            throw new IllegalStateException("Connection interrupted", e);
        }
    }

    private void updateChatView(User user) {
        main.getHome().getChat1().getChat_Body1().clearMessages();
        main.getHome().getChat1().getChat_Title1().setUserName(user.getUserName());
        displayMessageUser(user);
        main.getHome().getChat1().refresh();

    }

    public void updateUserList() {
        List<User> users = userService.getListUser();
        System.out.println(users);
        main.getHome().getMenu_Right1().setPeople(users);
    }

    public void displayMessageUser(User user) {
        for (int i = 0; i < localMessages.size(); i++) {
            if (localMessages.get(i).getUser().equals(user)) {
                List<Message> messages = localMessages.get(i).getMessage();
                for (Message message : messages) {
                    if (message.getSenderId() == user.getId()) {
                        main.getHome().getChat1().getChat_Body1().addItemLeft(message.getContent(), user);
                    } else {
                        main.getHome().getChat1().getChat_Body1().addItemRight(message.getContent());

                    }
                }
            }
        }
    }

    private void searchAction() {
        String searchString = this.main.getHome().getMenu_Right1().getJTextField1().getText();
        List<User> users = userService.findUSer(searchString);
        System.out.println(users);

        this.main.getHome().getMenu_Right1().setPeople(users);

    }

    public void addToLocalMessage(LocalMessage e) {
        this.localMessages.add(e);
    }

    private void addMessage(Integer userId, Message message) {
        for (LocalMessage localMessage : this.localMessages) {
            if (localMessage.getUser().getId() == userId) {
                if (localMessage.getMessage() != null) {
                    localMessage.getMessage().add(message);
                }
            }
        }
    }

}
