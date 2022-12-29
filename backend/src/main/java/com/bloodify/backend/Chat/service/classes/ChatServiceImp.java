package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;

import jakarta.transaction.Transactional;

@Service
public class ChatServiceImp implements ChatService {


    final ChatMessageDao chatMessageDao;


    final ChatMessageMapper chatMessageMapper;

    final AcceptRepository acceptRepository;


    final UserDAO userDAO;


    public ChatServiceImp(UserDAO userDAO, AcceptRepository acceptRepository, ChatMessageDao chatMessageDao, ChatMessageMapper chatMessageMapper) {
        this.userDAO = userDAO;
        this.acceptRepository = acceptRepository;
        this.chatMessageDao = chatMessageDao;
        this.chatMessageMapper = chatMessageMapper;
    }


    @Override
    @Transactional
    public boolean saveMessage(ChatMessageDto message) throws Exception {
        ChatMessage chatMessage = this.chatMessageMapper.dtoToEntity(message);
        Integer recipientID = chatMessage.getPostOwnerID();
        if(chatMessage.getDirection()){
            recipientID = chatMessage.getDonorID();
        }
        chatMessage.setNewMsgFor(recipientID);
        this.acceptRepository.save(chatMessage.getAcceptedPost());
        chatMessage.setMessageID(null);
        return this.chatMessageDao.saveMessage(chatMessage);
    }

    @Override
    @Transactional
    public List<ChatMessageDto> loadChatMessages(String email, Integer postID, Integer donorID) {
        List<ChatMessage> chatMessages = this.chatMessageDao.findChatMessages(postID, donorID);
        
        if(!chatMessages.isEmpty()){
            User user = userDAO.findUserByEmail(email);
            ChatMessage firstMessage = chatMessages.get(0);
            if(firstMessage.getNewMsgFor() == user.getUserID()){
                firstMessage.setNewMsgFor(-1);
                this.acceptRepository.save(firstMessage.getAcceptedPost());
            }
        }
        return chatMessages
                .stream()
                .map((chatMessage) -> this.chatMessageMapper.entityToDto(chatMessage))
                .toList();
    }


    @Override
    public void notifyRecipient(ChatMessageRequest message) {
        // TODO Auto-generated method stub
        
    }

}
