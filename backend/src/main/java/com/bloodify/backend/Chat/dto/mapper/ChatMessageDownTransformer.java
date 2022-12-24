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
public class ChatMessageDownTransformer {
    @Autowired
    ChatDao chatDao;
    @Autowired
    UserDAO userDAO;

    public ChatMessageDto transform(ChatMessageRequest chatMessageRequest) {
        return new ChatMessageDto(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getChatID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessage transform(ChatMessageDto chatMessageDto) throws ChatNotFoundException, SenderNotFoundException, RecipientNotFoundException {
        Chat chat;
        User sender, recipient;
        try {
            chat = chatDao.findByID(chatMessageDto.getChatID());
        } catch (Exception e) {
            throw new ChatNotFoundException();
        }
        try{
            sender = userDAO.findByID(chatMessageDto.getSenderID());

        }catch (Exception e) {
            throw new SenderNotFoundException();
        }
        try{
            recipient = userDAO.findByID(chatMessageDto.getSenderID());

        }catch (Exception e) {
            throw new RecipientNotFoundException();
        }

        return new ChatMessage(chatMessageDto.getChatID(), chat, sender, recipient, chatMessageDto.getContent(), chatMessageDto.getTimestamp());
    }
}
