package com.bloodify.backend.Chat.dto.mapper;


import org.springframework.stereotype.Component;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.exceptions.DonorNotFoundException;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;

@Component
public class ChatMapper implements Mapper<ChatRequest, ChatDto, Chat> {


    PostDao postDao;

    UserDAO userDAO;
    
    public ChatMapper(PostDao postDao, UserDAO userDAO) {
        this.postDao = postDao;
        this.userDAO = userDAO;
    }

    public ChatDto requestToDto(ChatRequest chatRequest){
        if(chatRequest == null) return null;

        return new ChatDto(chatRequest.getChatID(), chatRequest.getPostID(), chatRequest.getDonorID());
    }

    public Chat dtoToEntity(ChatDto chatDto) throws PostNotFoundException, DonorNotFoundException{
        if(chatDto == null) return null;
        Post post = postDao.getPostByID(chatDto.getPostID());
        if(post == null)    throw new PostNotFoundException();


        User donor = userDAO.findByID(chatDto.getDonorID());
        if(donor == null) throw new DonorNotFoundException();

        return new Chat(chatDto.getChatID(), post, donor);
    }

    
    public ChatRequest dtoToRequest(ChatDto chatDto){
        if(chatDto == null) return null;

        return new ChatRequest(chatDto.getChatID(), chatDto.getPostID(), chatDto.getDonorID());
    }

    public ChatDto entityToDto(Chat chat){
        if(chat == null) return null;

        return new ChatDto(chat.getChatID(), chat.getPost().getPostID(), chat.getDonor().getUserID());
    }

}
