package com.bloodify.backend.Chat.repository.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;
import com.bloodify.backend.Chat.repository.interfaces.ChatRepository;

@Service
public class ChatDaoImp implements ChatDao {

    ChatRepository repository;

    

    public ChatDaoImp(ChatRepository chatRepository) {
        this.repository = chatRepository;
    }

    @Override
    public boolean saveChat(Chat chat) {
        repository.save(chat);
        return true;
    }

    @Override
    public List<Chat> findByPostID(int postID) {
        return repository.findByPostPostID(postID);
    }

    @Override
    public Chat findByID(Integer chatID) {
        return repository.findById(chatID).orElse(null);
    }

    @Override
    public List<Chat> findByDonorID(Integer donorID) {
        return repository.findByDonorUserID(donorID);
    }



    
}
