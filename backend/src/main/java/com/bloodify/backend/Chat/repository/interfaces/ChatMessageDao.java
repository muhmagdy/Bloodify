package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.model.entities.ChatMessage;

public interface ChatMessageDao {
    
    boolean saveMessage(ChatMessage message);

    List<ChatMessage> findChatMessages(int chatID);

}
