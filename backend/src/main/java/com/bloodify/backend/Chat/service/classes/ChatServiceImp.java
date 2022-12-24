package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageTransformer;
import com.bloodify.backend.Chat.dto.mapper.ChatTransformer;
import com.bloodify.backend.Chat.exceptions.ChatNotFoundException;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;

@Service
public class ChatServiceImp implements ChatService {

    ChatDao chatDao;

    ChatMessageDao chatMessageDao;

    ChatTransformer chatTransformer;

    ChatMessageTransformer chatMessageTransformer;

    @Autowired
    public ChatServiceImp(ChatDao chatDao, ChatMessageDao chatMessageDao,
            ChatTransformer chatTransformer, ChatMessageTransformer chatMessageTransformer) {
        this.chatDao = chatDao;
        this.chatMessageDao = chatMessageDao;
        this.chatTransformer = chatTransformer;
        this.chatMessageTransformer = chatMessageTransformer;
    }

    @Override
    public boolean saveChat(ChatDto chatDto) throws PostNotFoundException, UserNotFoundException {
        Chat chat = chatTransformer.transformDown(chatDto);
        return this.chatDao.saveChat(chat);
    }

    @Override
    public List<ChatDto> loadChats(int postID) {
        List<Chat> chats = this.chatDao.findByPostID(postID);

        return chats
                .stream()
                .map((chat) -> this.chatTransformer.transformUp(chat))
                .toList();
    }

    @Override
    public boolean saveMessage(ChatMessageDto message)
            throws ChatNotFoundException, SenderNotFoundException, RecipientNotFoundException {
        ChatMessage chatMessage = this.chatMessageTransformer.transformDown(message);

        return this.chatMessageDao.saveMessage(chatMessage);
    }

    @Override
    public List<ChatMessageDto> loadChatMessages(int chatID) {
        List<ChatMessage> chatMessages = this.chatMessageDao.findChatMessages(chatID);
        return chatMessages
                .stream()
                .map((chatMessage) -> this.chatMessageTransformer.transformUp(chatMessage))
                .toList();
    }
}
