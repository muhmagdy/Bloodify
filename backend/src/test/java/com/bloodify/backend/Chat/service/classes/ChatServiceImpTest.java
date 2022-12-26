package com.bloodify.backend.Chat.service.classes;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.model.entities.User;
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

    ChatMessageDto chatMessageDto;
    List<ChatMessage> msgs;
    List<ChatMessageDto> msgsDto;


    ChatMessage mockMsg(int i){
        ChatMessage msg  = Mockito.mock(ChatMessage.class);
        when(msg.getMessageID()).thenReturn(i);
        when(msg.getContent()).thenReturn(String.valueOf(i*234));
        return msg;
    }
    ChatMessageDto mockMsgDto(int i){
        ChatMessageDto msg  = Mockito.mock(ChatMessageDto.class);
        when(msg.getMessageID()).thenReturn(i);
        when(msg.getContent()).thenReturn(String.valueOf(i*234));
        return msg;
    }

    @BeforeEach
    void init(){
        msgDao = Mockito.mock(ChatMessageDao.class);
        chatDao = Mockito.mock(ChatDao.class);
        chatMapper = Mockito.mock(ChatMapper.class);
        msgMapper = Mockito.mock(ChatMessageMapper.class);
        msgs = new LinkedList<>();
        msgsDto = new LinkedList<>();


        for(int i = 0; i < 5; i++){
            msgs.add(mockMsg(i));
            msgsDto.add(mockMsgDto(i));
        }
        chatMessageDto =  Mockito.mock(ChatMessageDto.class);
        service = new ChatServiceImp(chatDao, msgDao, chatMapper, msgMapper);
    }
    
    @Test
    void testLoadChatMessages_happy() throws Exception {
        when(msgDao.findChatMessages(3, 5)).thenReturn(msgs);
        Iterator<ChatMessage> msgIter = msgs.iterator();
        Iterator<ChatMessageDto> dtoIter = msgsDto.iterator();
        while(msgIter.hasNext() && dtoIter.hasNext()){
            when(msgMapper.entityToDto(msgIter.next())).thenReturn(dtoIter.next());
        }
        List<ChatMessageDto> actual = assertDoesNotThrow(() -> service.loadChatMessages(3, 5));

        assertEquals(msgsDto, actual);
    }

    @Test
    void testLoadChatMessages_noMessages() throws Exception {
        when(msgDao.findChatMessages(3, 5)).thenReturn(List.of());
        

        List<ChatMessageDto> actual = assertDoesNotThrow(() -> service.loadChatMessages(3, 5));

        assertEquals(new LinkedList<ChatMessageDto>(), actual);
    }

    @Test
    void testSaveMessage_happy() throws Exception {
        ChatMessage msg = Mockito.spy(ChatMessage.class);

        when(msgMapper.dtoToEntity(chatMessageDto)).thenReturn(msg);

        //TODO

        // boolean actual = assertDoesNotThrow(() -> service.saveMessage(chatMessageDto));

        // assertTrue(actual);
    }
}
