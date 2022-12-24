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
public class ChatMessageTransformer {

    ChatDao chatDao;

    UserDAO userDAO;

    @Autowired
    public ChatMessageTransformer(ChatDao chatDao, UserDAO userDAO) {
        this.chatDao = chatDao;
        this.userDAO = userDAO;
    }

    public ChatMessageDto transformDown(ChatMessageRequest chatMessageRequest) {
        return new ChatMessageDto(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getChatID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessage transformDown(ChatMessageDto chatMessageDto)
            throws ChatNotFoundException, SenderNotFoundException, RecipientNotFoundException {

        Chat chat = chatDao.findByID(chatMessageDto.getChatID());
        if (chat == null)
            throw new ChatNotFoundException();

        User sender = userDAO.findByID(chatMessageDto.getSenderID());
        if (sender == null)
            throw new SenderNotFoundException();

        User recipient = userDAO.findByID(chatMessageDto.getSenderID());
        if (recipient == null)
            throw new RecipientNotFoundException();

        return new ChatMessage(
                chatMessageDto.getChatID(),
                chat,
                sender,
                recipient,
                chatMessageDto.getContent(),
                chatMessageDto.getTimestamp());
    }

    public ChatMessageRequest transformUp(ChatMessageDto chatMessageRequest) {
        return new ChatMessageRequest(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getChatID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessageDto transformUp(ChatMessage chatMessage) {

        return new ChatMessageDto(
                chatMessage.getMessageID(),
                chatMessage.getChat().getChatID(),
                chatMessage.getSender().getUserID(),
                chatMessage.getRecipient().getUserID(),
                chatMessage.getContent(),
                chatMessage.getTimestamp());
    }

    

}
