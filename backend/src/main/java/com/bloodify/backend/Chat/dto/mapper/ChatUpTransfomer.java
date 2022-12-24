package com.bloodify.backend.Chat.dto.mapper;

import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.model.entities.Chat;

public class ChatUpTransfomer {
    public ChatRequest transform(ChatDto chatDto){
        return new ChatRequest(chatDto.getChatID(), chatDto.getPostID(), chatDto.getDonorID());
    }

    public ChatDto transform(Chat chat){

        return new ChatDto(chat.getChatID(), chat.getPost().getPostID(), chat.getDonor().getUserID());
    }
}
