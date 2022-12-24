package com.bloodify.backend.Chat.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.ChatNotFoundException;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;

@Service
public class ChatMessageUpTransformer {
    @Autowired
    ChatDao chatDao;
    @Autowired
    UserDAO userDAO;

    public ChatMessageRequest transform(ChatMessageDto chatMessageRequest) {
        return new ChatMessageRequest(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getChatID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessageDto transform(ChatMessage chatMessage) {

        return new ChatMessageDto(
                chatMessage.getMessageID(),
                chatMessage.getChat().getChatID(),
                chatMessage.getSender().getUserID(),
                chatMessage.getRecipient().getUserID(),
                chatMessage.getContent(),
                chatMessage.getTimestamp());
    }
}
