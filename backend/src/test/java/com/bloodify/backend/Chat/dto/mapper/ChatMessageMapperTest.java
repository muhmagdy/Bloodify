package com.bloodify.backend.Chat.dto.mapper;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.exceptions.RecipientNotFoundException;
import com.bloodify.backend.Chat.exceptions.SenderNotFoundException;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.UserRequests.exceptions.AcceptedPostNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;

public class ChatMessageMapperTest {
    
    AcceptRepository acceptRepository;
    UserDAO userDAO;

    AcceptedPost acceptedPost;
    Post post;
    User donor, sender, recipient;

    ChatMessageMapper chatMessageMapper;

    @BeforeEach
    void init() {
        acceptRepository = Mockito.mock(AcceptRepository.class);
        userDAO = Mockito.mock(UserDAO.class);
        acceptedPost = Mockito.mock(AcceptedPost.class);
        post = Mockito.mock(Post.class);
        donor = Mockito.mock(User.class);
        sender = Mockito.mock(User.class);
        recipient = Mockito.mock(User.class);
        chatMessageMapper = new ChatMessageMapper(acceptRepository, userDAO);
    }

    @Test
    void testDtoToEntity_happy() {
        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(3);
        when(dto.getDonorID()).thenReturn(1);
        when(dto.getSenderID()).thenReturn(2);
        when(dto.getRecipientID()).thenReturn(5);
        when(dto.getMessageID()).thenReturn(1);

        when(acceptRepository.findByPostPostIDAndUserUserID(3, 1)).thenReturn(acceptedPost);
        when(userDAO.findByID(1)).thenReturn(donor);
        when(userDAO.findByID(2)).thenReturn(sender);
        when(userDAO.findByID(5)).thenReturn(recipient);

        ChatMessage msg = assertDoesNotThrow(() -> chatMessageMapper.dtoToEntity(dto));

        assertTrue(msg.getAcceptedPost().equals(acceptedPost));
        assertTrue(msg.getSender().equals(sender));
        assertTrue(msg.getRecipient().equals(recipient));
        assertEquals(dto.getContent(), msg.getContent());
        assertEquals(dto.getTimestamp(), msg.getTimestamp());
        assertEquals(dto.getMessageID(), msg.getMessageID());
    }

    @Test
    void testDtoToEntity_nullInput() {
        ChatMessage msg = assertDoesNotThrow(() -> chatMessageMapper.dtoToEntity(null));
        assertNull(msg);
    }

    @Test
    void testDtoToEntity_acceptedPostNotFound() {
        when(acceptRepository.findByPostPostIDAndUserUserID(1, 2)).thenReturn(null);
        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(1);
        when(dto.getDonorID()).thenReturn(2);

        assertThrows(AcceptedPostNotFoundException.class, () -> chatMessageMapper.dtoToEntity(dto));


    }

    @Test
    void testDtoToEntity_senderNotFound() {

        when(acceptRepository.findByPostPostIDAndUserUserID(1, 1)).thenReturn(acceptedPost);
        when(userDAO.findByID(5)).thenReturn(null);


        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(1);
        when(dto.getDonorID()).thenReturn(1);
        when(dto.getSenderID()).thenReturn(5);


        assertThrows(SenderNotFoundException.class, () -> chatMessageMapper.dtoToEntity(dto));

    }

    @Test
    void testDtoToEntity_recipientNotFound() {
        when(acceptRepository.findByPostPostIDAndUserUserID(1, 1)).thenReturn(acceptedPost);

        when(userDAO.findByID(5)).thenReturn(sender);
        when(userDAO.findByID(6)).thenReturn(null);

        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(1);
        when(dto.getDonorID()).thenReturn(1);
        when(dto.getSenderID()).thenReturn(5);
        when(dto.getRecipientID()).thenReturn(6);

        assertThrows(RecipientNotFoundException.class, () -> chatMessageMapper.dtoToEntity(dto));

    }

    @Test
    void testDtoToRequest_happy() {
        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(3);
        when(dto.getSenderID()).thenReturn(2);
        when(dto.getRecipientID()).thenReturn(5);
        when(dto.getMessageID()).thenReturn(1);

        ChatMessageRequest request = assertDoesNotThrow(() -> chatMessageMapper.dtoToRequest(dto));

        assertEquals(dto.getPostID(), request.getPostID());
        assertEquals(dto.getSenderID(), request.getSenderID());
        assertEquals(dto.getRecipientID(), request.getRecipientID());
        assertEquals(dto.getContent(), request.getContent());
        assertEquals(dto.getTimestamp(), request.getTimestamp());
        assertEquals(dto.getMessageID(), request.getMessageID());
    }

    @Test
    void testDtoToRequest_nullInput() {
        ChatMessageRequest msg = assertDoesNotThrow(() -> chatMessageMapper.dtoToRequest(null));
        assertNull(msg);
    }

    @Test
    void testEntityToDto_happy() {
        ChatMessage msg = Mockito.mock(ChatMessage.class);
        when(msg.getAcceptedPost()).thenReturn(acceptedPost);
        when(acceptedPost.getPost()).thenReturn(post);
        when(acceptedPost.getUser()).thenReturn(donor);


        when(msg.getSender()).thenReturn(sender);
        when(msg.getRecipient()).thenReturn(recipient);
        when(msg.getMessageID()).thenReturn(1);

        ChatMessageDto dto = assertDoesNotThrow(() -> chatMessageMapper.entityToDto(msg));

        assertTrue(msg.getAcceptedPost().equals(acceptedPost));
        assertTrue(msg.getSender().equals(sender));
        assertTrue(msg.getRecipient().equals(recipient));
        assertEquals(dto.getContent(), msg.getContent());
        assertEquals(dto.getTimestamp(), msg.getTimestamp());
        assertEquals(dto.getMessageID(), msg.getMessageID());
    }

    @Test
    void testEntityToDto_nullInput() {
        ChatMessageDto msg = assertDoesNotThrow(() -> chatMessageMapper.entityToDto(null));
        assertNull(msg);
    }

    @Test
    void testRequestToDto_happy() {
        ChatMessageRequest request = Mockito.mock(ChatMessageRequest.class);
        when(request.getPostID()).thenReturn(1);
        when(request.getSenderID()).thenReturn(3);
        when(request.getRecipientID()).thenReturn(5);
        when(request.getMessageID()).thenReturn(1);

        ChatMessageDto dto = assertDoesNotThrow(() -> chatMessageMapper.requestToDto(request));

        assertEquals(request.getPostID(), dto.getPostID());
        assertEquals(request.getSenderID(), dto.getSenderID());
        assertEquals(request.getRecipientID(), dto.getRecipientID());
        assertEquals(request.getContent(), dto.getContent());
        assertEquals(request.getTimestamp(), dto.getTimestamp());
        assertEquals(request.getMessageID(), dto.getMessageID());

    }

    @Test
    void testRequestToDto_nullInput() {
        ChatMessageDto msg = assertDoesNotThrow(() -> chatMessageMapper.requestToDto(null));
        assertNull(msg);
    }
}
