package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatDownTransformer;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageDownTransformer;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageUpTransformer;
import com.bloodify.backend.Chat.dto.mapper.ChatUpTransfomer;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatRepository;
import com.bloodify.backend.Chat.service.interfaces.ChatService;

@Service
public class ChatServiceImp implements ChatService {

    @Autowired
    ChatDao dao;

        
    @Autowired
    ChatDownTransformer chatDownTransformer;

    
    @Autowired
    ChatUpTransfomer chatUpTransformer;

    @Autowired
    ChatMessageDownTransformer chatMessageDownTransformer;

    
    @Autowired
    ChatMessageUpTransformer chatMessageUpTransformer;



    @Override
    public boolean saveChat(ChatDto chatDto) {
        Chat chat = chatDownTransformer.transform(chatDto);


        return false;
    }

    @Override
    public List<ChatDto> loadChats(int postID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ChatDto loadChat(int postID, int donorID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean saveMessage(ChatMessageDto message) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ChatMessageDto> loadChatMessages(int chatID) {
        // TODO Auto-generated method stub
        return null;
    }
}
