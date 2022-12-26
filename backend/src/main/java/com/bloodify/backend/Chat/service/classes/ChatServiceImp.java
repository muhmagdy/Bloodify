package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.Mapper;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;

@Service
public class ChatServiceImp implements ChatService {

    ChatDao chatDao;

    ChatMessageDao chatMessageDao;

    Mapper<ChatRequest, ChatDto, Chat> chatMapper;

    Mapper<ChatMessageRequest, ChatMessageDto, ChatMessage> chatMessageMapper;


    public ChatServiceImp(ChatDao chatDao,
            ChatMessageDao chatMessageDao,
            Mapper<ChatRequest, ChatDto, Chat> chatMapper,
            Mapper<ChatMessageRequest, ChatMessageDto, ChatMessage> chatMessageMapper) {
        this.chatDao = chatDao;
        this.chatMessageDao = chatMessageDao;
        this.chatMapper = chatMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Override
    public boolean saveChat(ChatDto chatDto) throws Exception {
        Chat chat = chatMapper.dtoToEntity(chatDto);
        return this.chatDao.saveChat(chat);
    }

    @Override
    public List<ChatDto> loadChats(int postID) {
        List<Chat> chats = this.chatDao.findByPostID(postID);

        return chats
                .stream()
                .map((chat) -> this.chatMapper.entityToDto(chat))
                .toList();
    }

    @Override
    public List<ChatDto> loadChats(Integer donorID) {
        List<Chat> chats = this.chatDao.findByDonorID(donorID);

        return chats
                .stream()
                .map((chat) -> this.chatMapper.entityToDto(chat))
                .toList();
    }

    // TODO: for chat creation call save chat first from the UI.
    @Override
    public boolean saveMessage(ChatMessageDto message) throws Exception {
        ChatMessage chatMessage = this.chatMessageMapper.dtoToEntity(message);

        return this.chatMessageDao.saveMessage(chatMessage);
    }

    @Override
    public List<ChatMessageDto> loadChatMessages(Integer postID, Integer donorID) {
        List<ChatMessage> chatMessages = this.chatMessageDao.findChatMessages(postID, donorID);
        return chatMessages
                .stream()
                .map((chatMessage) -> this.chatMessageMapper.entityToDto(chatMessage))
                .toList();
    }

}
