package com.bloodify.backend.Chat.service.classes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMapper;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.dto.mapper.Mapper;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;

public class ChatServiceImpTest {

    ChatMessageDao msgDao;
    ChatDao chatDao;
    Mapper<ChatRequest, ChatDto, Chat> chatMapper;
    Mapper<ChatMessageRequest, ChatMessageDto, ChatMessage> msgMapper;

    ChatService service;

    @BeforeEach
    void init(){
        msgDao = Mockito.mock(ChatMessageDao.class);
        chatDao = Mockito.mock(ChatDao.class);
        chatMapper = Mockito.mock(ChatMapper.class);
        msgMapper = Mockito.mock(ChatMessageMapper.class);
        service = new ChatServiceImp(chatDao, msgDao, chatMapper, msgMapper);
    }
    
    @Test
    void testLoadChatMessages() throws Exception {
        ChatDto chatDto = new ChatDto(0, 0, 0);
        when(chatMapper.dtoToEntity(chatDto)).thenThrow(new PostNotFoundException());

        assertDoesNotThrow(() -> service.saveChat(chatDto));
    }

    @Test
    void testLoadChats() {

    }

    @Test
    void testLoadChats2() {

    }

    @Test
    void testSaveChat() {

    }

    @Test
    void testSaveMessage() {

    }
}
