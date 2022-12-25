package com.bloodify.backend.Chat.repository.classes;

import java.util.List;


import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageRepository;

@Service
public class ChatMessageDaoImp implements ChatMessageDao{

    ChatMessageRepository repository;


    public ChatMessageDaoImp(ChatMessageRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean saveMessage(ChatMessage message) {
        repository.save(message);
        return true;
    }

    @Override
    public List<ChatMessage> findChatMessages(int chatID) {
        return repository.findByChatChatID(chatID);
    }
    
}
