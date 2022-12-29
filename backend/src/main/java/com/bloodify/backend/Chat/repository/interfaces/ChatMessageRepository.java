package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bloodify.backend.Chat.model.entities.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {


    // save is ls already implemented

    List<ChatMessage> findByAcceptedPostPostPostIDAndAcceptedPostUserUserIDOrderByTimestampDesc(Integer postID, Integer donorID);
}
