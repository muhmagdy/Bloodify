package com.bloodify.backend.Chat.repository.classes;


import java.util.List;
import java.util.Map;

import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;


public class ChatDaoMock implements ChatDao{

    private Map<Integer, Chat> nonFancyDB;
    private int idCounter;
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
