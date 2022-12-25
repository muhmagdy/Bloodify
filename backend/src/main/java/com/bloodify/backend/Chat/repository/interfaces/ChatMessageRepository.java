package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.model.entities.ChatMessagePk;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessagePk> {

    // boolean saveMessage(ChatMessage message);
    // save is ls already implemented

    List<ChatMessage> findByChatChatID(Integer chatID);
}
