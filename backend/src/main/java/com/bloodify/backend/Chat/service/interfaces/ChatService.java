package com.bloodify.backend.Chat.service.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;

public interface ChatService {

    boolean saveChat(ChatDto chatDto);

    List<ChatDto> loadChats(int postID);

    ChatDto loadChat(int postID, int donorID);

    boolean saveMessage(ChatMessageDto message);

    List<ChatMessageDto> loadChatMessages(int chatID);



}
