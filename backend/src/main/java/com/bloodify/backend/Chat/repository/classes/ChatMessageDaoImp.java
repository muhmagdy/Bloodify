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
        return repository.save(message) != null;
    }

    @Override
    public List<ChatMessage> findChatMessages(Integer postID,  Integer donorID) {
        return repository.findByAcceptedPostPostPostIDAndAcceptedPostUserUserID(postID, donorID);
    }

    
}
