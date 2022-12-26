package com.bloodify.backend.Chat.service.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
public interface ChatService {

    boolean saveChat(ChatDto chatDto) throws Exception;

    List<ChatDto> loadChats(int postID);

    List<ChatDto> loadChats(Integer donorID);

    boolean saveMessage(ChatMessageDto message) throws Exception;

    List<ChatMessageDto> loadChatMessages(Integer postID, Integer donorID);



}
