package com.bloodify.backend.Chat.repository.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatRepository;

@Service
public class ChatDaoImp implements ChatDao {

    ChatRepository chatRepository;

    
    @Autowired
    public ChatDaoImp(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    public boolean saveChat(Chat chat) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Chat> findByPostID(int postID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Chat findByID(Integer chatID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Chat> findByDonorID(Integer donorID) {
        // TODO Auto-generated method stub
        return null;
    }



    
}
