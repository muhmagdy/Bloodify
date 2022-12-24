package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodify.backend.Chat.model.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    boolean saveChat(Chat chat);

    List<Chat> findByPostID(Integer postID);
    
}
