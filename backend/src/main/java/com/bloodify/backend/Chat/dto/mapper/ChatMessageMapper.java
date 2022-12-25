package com.bloodify.backend.Chat.dto.mapper;

import org.springframework.stereotype.Component;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.ChatNotFoundException;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.MessageStatus;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;

@Component
public class ChatMessageMapper implements Mapper<ChatMessageRequest, ChatMessageDto, ChatMessage> {

    ChatDao chatDao;

    UserDAO userDAO;


    public ChatMessageMapper(ChatDao chatDao, UserDAO userDAO) {
        this.chatDao = chatDao;
        this.userDAO = userDAO;
    }

    public ChatMessageDto requestToDto(ChatMessageRequest chatMessageRequest) {
        return new ChatMessageDto(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getChatID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessage dtoToEntity(ChatMessageDto chatMessageDto)
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
                chatMessageDto.getTimestamp(),
                MessageStatus.DELIVERED);
    }

    public ChatMessageRequest dtoToRequest(ChatMessageDto chatMessageDto) {
        return new ChatMessageRequest(
                chatMessageDto.getMessageID(),
                chatMessageDto.getChatID(),
                chatMessageDto.getSenderID(),
                chatMessageDto.getRecipientID(),
                chatMessageDto.getContent(),
                chatMessageDto.getTimestamp());
    }

    public ChatMessageDto entityToDto(ChatMessage chatMessage) {

        return new ChatMessageDto(
                chatMessage.getMessageID(),
                chatMessage.getChat().getChatID(),
                chatMessage.getSender().getUserID(),
                chatMessage.getRecipient().getUserID(),
                chatMessage.getContent(),
                chatMessage.getTimestamp());
    }

}
