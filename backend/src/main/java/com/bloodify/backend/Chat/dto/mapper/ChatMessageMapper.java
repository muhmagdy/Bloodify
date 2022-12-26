package com.bloodify.backend.Chat.dto.mapper;

import org.springframework.stereotype.Component;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.UserRequests.exceptions.AcceptedPostNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;

@Component
public class ChatMessageMapper{

    AcceptRepository acceptRepository;

    UserDAO userDAO;

    public ChatMessageMapper(AcceptRepository acceptRepository, UserDAO userDAO) {
        this.acceptRepository = acceptRepository;
        this.userDAO = userDAO;
    }

    public ChatMessageDto requestToDto(ChatMessageRequest chatMessageRequest) {
        if (chatMessageRequest == null)
            return null;
        return new ChatMessageDto(
                chatMessageRequest.getMessageID(),
                chatMessageRequest.getPostID(),
                chatMessageRequest.getDonorID(),
                chatMessageRequest.getSenderID(),
                chatMessageRequest.getRecipientID(),
                chatMessageRequest.getContent(),
                chatMessageRequest.getTimestamp());
    }

    public ChatMessage dtoToEntity(ChatMessageDto chatMessageDto)
            throws AcceptedPostNotFoundException, SenderNotFoundException, RecipientNotFoundException {

        if (chatMessageDto == null)
            return null;

        // Post post = postDao.getPostByID(chatMessageDto.getPostID());
        // if (post == null)
        //     throw new PostNotFoundException();

        // User donor = userDAO.findByID(chatMessageDto.getDonorID());
        // if (donor == null)
        //     throw new DonorNotFoundException();

        AcceptedPost acceptedPost = acceptRepository
                                    .findByPostPostIDAndUserUserID(
                                        chatMessageDto.getPostID(), chatMessageDto.getDonorID());

        if(acceptedPost == null)
                throw new AcceptedPostNotFoundException();

        User sender = userDAO.findByID(chatMessageDto.getSenderID());
        if (sender == null)
            throw new SenderNotFoundException();

        User recipient = userDAO.findByID(chatMessageDto.getRecipientID()); // thanks mockito
        if (recipient == null)
            throw new RecipientNotFoundException();

        return new ChatMessage(
                chatMessageDto.getMessageID(), // thanks mockit
                acceptedPost,
                sender,
                recipient,
                chatMessageDto.getContent(),
                chatMessageDto.getTimestamp());
    }

    public ChatMessageRequest dtoToRequest(ChatMessageDto chatMessageDto) {
        if (chatMessageDto == null)
            return null;
        return new ChatMessageRequest(
                chatMessageDto.getMessageID(),
                chatMessageDto.getPostID(),
                chatMessageDto.getDonorID(),
                chatMessageDto.getSenderID(),
                chatMessageDto.getRecipientID(),
                chatMessageDto.getContent(),
                chatMessageDto.getTimestamp());
    }

    public ChatMessageDto entityToDto(ChatMessage chatMessage) {
        if (chatMessage == null)
            return null;

        return new ChatMessageDto(
                chatMessage.getMessageID(),
                chatMessage.getAcceptedPost().getPost().getPostID(),
                chatMessage.getAcceptedPost().getUser().getUserID(),
                chatMessage.getSender().getUserID(),
                chatMessage.getRecipient().getUserID(),
                chatMessage.getContent(),
                chatMessage.getTimestamp());
    }

}
