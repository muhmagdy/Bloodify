package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodify.backend.Chat.model.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

    //Save is already implemented

    List<Chat> findByPostPostID(Integer postID);

    List<Chat> findByDonorUserID(Integer donorID);
    
}
