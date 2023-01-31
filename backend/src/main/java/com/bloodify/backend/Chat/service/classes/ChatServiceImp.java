package com.bloodify.backend.Chat.service.classes;

import java.util.List;

import com.bloodify.backend.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.exceptions.AcceptedPostNotFoundException;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;
import com.google.firebase.messaging.FirebaseMessagingException;

import jakarta.transaction.Transactional;
@RequiredArgsConstructor
@Service
public class ChatServiceImp implements ChatService {


    final ChatMessageDao chatMessageDao;


    final ChatMessageMapper chatMessageMapper;

    final AcceptRepository acceptRepository;


    final UserDAO userDAO;

    final NotificationService messagingService;
    final LoginSessionDAO loginSessionDAO;
    
    private Integer getRecipientID(ChatMessage chatMessage){
        Integer recipientID = chatMessage.getPostOwnerID();
        if(chatMessage.getDirection()){
            recipientID = chatMessage.getDonorID();
        }
        return recipientID;
    }


    @Override
    @Transactional
    public boolean saveMessage(ChatMessageDto message) throws Exception {
        ChatMessage chatMessage = this.chatMessageMapper.dtoToEntity(message);

        //get the recipient of this message and set the unread flag to be the recipient ID.
        Integer recipientID = getRecipientID(chatMessage);

        chatMessage.setNewMsgFor(recipientID);
        this.acceptRepository.save(chatMessage.getAcceptedPost());
        chatMessage.setMessageID(null);
        return this.chatMessageDao.saveMessage(chatMessage);
    }

    @Override
    @Transactional
    public List<ChatMessageDto> loadChatMessages(String email, Integer postID, Integer donorID) throws UserNotFoundException {
        List<ChatMessage> chatMessages = this.chatMessageDao.findChatMessages(postID, donorID);
        
        if(!chatMessages.isEmpty()){
            //When loading messages, if the unread flag is this user ID, unset it (-1).
            User user = userDAO.findUserByEmail(email);
            if(user == null)    throw new UserNotFoundException();
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
    public void notifyRecipient(ChatMessageDto message) throws AcceptedPostNotFoundException, SenderNotFoundException,
                                                                 RecipientNotFoundException, FirebaseMessagingException {
        ChatMessage chatMessage = this.chatMessageMapper.dtoToEntity(message);

        Integer recipientID = getRecipientID(chatMessage);

        User recipient = userDAO.findByID(recipientID);

        messagingService.sendNotification(recipient.getEmail(), "You may have new message.", null);
        
    }

}
