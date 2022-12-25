package com.bloodify.backend.Chat.dto.mapper;


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
public class ChatMapper implements Mapper<ChatRequest, ChatDto, Chat> {


    PostDao postDao;

    UserDAO userDAO;
    
    @Autowired
    public ChatMapper(PostDao postDao, UserDAO userDAO) {
        this.postDao = postDao;
        this.userDAO = userDAO;
    }

    public ChatDto requestToDto(ChatRequest chatRequest){
        return new ChatDto(chatRequest.getChatID(), chatRequest.getPostID(), chatRequest.getDonorID());
    }

    public Chat dtoToEntity(ChatDto chatDto) throws PostNotFoundException, UserNotFoundException{
        Post post = postDao.getPostByID(chatDto.getPostID());
        if(post == null)    throw new PostNotFoundException();


        User donor = userDAO.findByID(chatDto.getDonorID());
        if(donor == null) throw new UserNotFoundException();

        return new Chat(chatDto.getChatID(), post, donor);
    }

    
    public ChatRequest dtoToRequest(ChatDto chatDto){
        return new ChatRequest(chatDto.getChatID(), chatDto.getPostID(), chatDto.getDonorID());
    }

    public ChatDto entityToDto(Chat chat){

        return new ChatDto(chat.getChatID(), chat.getPost().getPostID(), chat.getDonor().getUserID());
    }

}
