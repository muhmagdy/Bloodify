package com.bloodify.backend.Chat.dto.mapper;

import static org.mockito.ArgumentMatchers.charThat;
import static org.mockito.ArgumentMatchers.nullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;

@Service
public class ChatDownTransformer {

    @Autowired
    PostDao postDao;
    @Autowired
    UserDAO userDAO;
    

    public ChatDto transform(ChatRequest chatRequest){
        return new ChatDto(chatRequest.getChatID(), chatRequest.getPostID(), chatRequest.getDonorID());
    }

    public Chat transform(ChatDto chatDto) throws PostNotFoundException{
        Post post = postDao.getPostByID(chatDto.getPostID());
        if(post == null)    throw new PostNotFoundException();


        User donor = userDAO.findByID(chatDto.getDonorID());
        if(donor == null) throw new UserNotFoundException();

        return new Chat(chatDto.getChatID(), post, donor);
    }
}
