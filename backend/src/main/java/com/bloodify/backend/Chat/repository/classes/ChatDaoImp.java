package com.bloodify.backend.Chat.repository.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatRepository;

@Service
public class ChatDaoImp implements ChatDao {

    @Autowired
    ChatRepository chatRepository;

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



    
}
