package com.bloodify.backend.Chat.service.interfaces;

import java.util.List;

import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.ChatNotFoundException;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;

public interface ChatService {

    boolean saveChat(ChatDto chatDto) throws PostNotFoundException, UserNotFoundException;

    List<ChatDto> loadChats(int postID);

    boolean saveMessage(ChatMessageDto message) throws ChatNotFoundException, SenderNotFoundException, RecipientNotFoundException;

    List<ChatMessageDto> loadChatMessages(int chatID);



}
