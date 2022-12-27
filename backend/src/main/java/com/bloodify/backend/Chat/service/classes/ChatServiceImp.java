package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;

@Service
public class ChatServiceImp implements ChatService {


    ChatMessageDao chatMessageDao;


    ChatMessageMapper chatMessageMapper;


    public ChatServiceImp(ChatMessageDao chatMessageDao, ChatMessageMapper chatMessageMapper) {
        this.chatMessageDao = chatMessageDao;
        this.chatMessageMapper = chatMessageMapper;
    }


    @Override
    public boolean saveMessage(ChatMessageDto message) throws Exception {
        ChatMessage chatMessage = this.chatMessageMapper.dtoToEntity(message);
        Integer recipientID = chatMessage.getAcceptedPost().getPost().getUser().getUserID();
        if(chatMessage.getDirection()){
            recipientID = chatMessage.getAcceptedPost().getUser().getUserID();
        }
        chatMessage.getAcceptedPost().setNewMsgFor(recipientID);
        return this.chatMessageDao.saveMessage(chatMessage);
    }

    @Override
    public List<ChatMessageDto> loadChatMessages(Integer postID, Integer donorID) {
        List<ChatMessage> chatMessages = this.chatMessageDao.findChatMessages(postID, donorID);
        return chatMessages
                .stream()
                .map((chatMessage) -> this.chatMessageMapper.entityToDto(chatMessage))
                .toList();
    }

}
