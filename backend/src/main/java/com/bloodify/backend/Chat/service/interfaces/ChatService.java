package com.bloodify.backend.Chat.service.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
public interface ChatService {

    boolean saveMessage(ChatMessageDto message) throws Exception;

    List<ChatMessageDto> loadChatMessages(String email, Integer postID, Integer donorID) throws UserNotFoundException;

    void notifyRecipient(ChatMessageRequest message);



}
