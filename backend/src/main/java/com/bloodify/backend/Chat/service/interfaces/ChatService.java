package com.bloodify.backend.Chat.service.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.AcceptedPostNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.google.firebase.messaging.FirebaseMessagingException;
public interface ChatService {

    boolean saveMessage(ChatMessageDto message) throws Exception;

    List<ChatMessageDto> loadChatMessages(String email, Integer postID, Integer donorID) throws UserNotFoundException;

    void notifyRecipient(ChatMessageDto message) throws AcceptedPostNotFoundException, SenderNotFoundException, RecipientNotFoundException, FirebaseMessagingException;



}
