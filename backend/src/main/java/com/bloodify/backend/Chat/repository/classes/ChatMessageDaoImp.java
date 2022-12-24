package com.bloodify.backend.Chat.repository.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageRepository;

@Service
public class ChatMessageDaoImp implements ChatMessageDao{

    ChatMessageRepository repository;

    @Autowired
    public ChatMessageDaoImp(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean saveMessage(ChatMessage message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ChatMessage> findChatMessages(int chatID) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
