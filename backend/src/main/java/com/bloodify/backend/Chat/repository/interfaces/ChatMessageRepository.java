package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.model.entities.ChatMessagePk;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, ChatMessagePk> {


    // save is ls already implemented

    List<ChatMessage> findByAcceptedPostPostPostIDAndAcceptedPostUserUserIDOrderByTimestampDesc(Integer postID, Integer donorID);
}
