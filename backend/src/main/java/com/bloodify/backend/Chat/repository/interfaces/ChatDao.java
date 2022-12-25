package com.bloodify.backend.Chat.repository.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.model.entities.Chat;

public interface ChatDao {
    boolean saveChat(Chat chat);

    List<Chat> findByPostID(int postID);

    List<Chat> findByDonorID(Integer donorID);

    Chat findByID(Integer chatID);
}
