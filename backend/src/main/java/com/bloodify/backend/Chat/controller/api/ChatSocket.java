package com.bloodify.backend.Chat.controller.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageNotification;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ChatSocket {

    private final SocketIOServer server;
    private final ChatService service;
    private final ChatMessageMapper mapper;


    public ChatSocket(SocketIOServer server,  ChatService service, ChatMessageMapper mapper) {
        this.server = server;
        this.service = service;
        this.mapper = mapper;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", ChatMessageRequest.class, onChatReceived());

    }

    private DataListener<ChatMessageRequest> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info(data.toString());
            String room = data.getPostID() + "_" + data.getDonorID();
            sendMessage(room, "get_message", senderClient, data);

        };
    }

    private void sendMessage(String room, String eventName, SocketIOClient senderClient, ChatMessageRequest message) throws Exception {

        boolean isRecipientOnline = false;
        try {
         this.service.saveMessage(mapper.requestToDto(message));
            
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return;
        }
        log.info(message.getContent());
        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                isRecipientOnline = true;
                client.sendEvent(eventName, new ChatMessageNotification(message.getPostID(), message.getDonorID()));
            }
        }

        if(!isRecipientOnline){
            this.service.notifyRecipient(message);
        }
    }

    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}