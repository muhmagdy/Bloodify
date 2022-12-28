package com.bloodify.backend.Chat.dto.mapper;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
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
    User donor;

    ChatMessageMapper chatMessageMapper;

    @BeforeEach
    void init() {
        acceptRepository = Mockito.mock(AcceptRepository.class);
        userDAO = Mockito.mock(UserDAO.class);
        acceptedPost = Mockito.mock(AcceptedPost.class);
        post = Mockito.mock(Post.class);
        donor = Mockito.mock(User.class);
        chatMessageMapper = new ChatMessageMapper(acceptRepository, userDAO);
    }

    @Test
    void testDtoToEntity_happy() {
        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(3);
        when(dto.getDonorID()).thenReturn(1);
        when(dto.getDirection()).thenReturn(true);
        when(dto.getMessageID()).thenReturn(1);

        when(acceptRepository.findByPostPostIDAndUserUserID(3, 1)).thenReturn(acceptedPost);
        when(userDAO.findByID(1)).thenReturn(donor);

        ChatMessage msg = assertDoesNotThrow(() -> chatMessageMapper.dtoToEntity(dto));

        assertTrue(msg.getAcceptedPost().equals(acceptedPost));
        assertEquals(dto.getDirection(), msg.getDirection());
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
    void testDtoToRequest_happy() {
        ChatMessageDto dto = Mockito.mock(ChatMessageDto.class);
        when(dto.getPostID()).thenReturn(3);
        when(dto.getDirection()).thenReturn(true);
        when(dto.getMessageID()).thenReturn(1);
        LocalDateTime dateTime = LocalDateTime.parse("2022-10-15T12:13");
        when(dto.getTimestamp()).thenReturn(dateTime);

        ChatMessageRequest request = assertDoesNotThrow(() -> chatMessageMapper.dtoToRequest(dto));

        assertEquals(dto.getPostID(), request.getPostID());
        assertEquals(dto.getDirection(), request.getDirection());
        assertEquals(dto.getContent(), request.getContent());
        assertEquals(dto.getTimestamp().toString(), request.getTimestamp());
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


        when(msg.getDirection()).thenReturn(true);
        when(msg.getMessageID()).thenReturn(1);

        ChatMessageDto dto = assertDoesNotThrow(() -> chatMessageMapper.entityToDto(msg));

        assertTrue(msg.getAcceptedPost().equals(acceptedPost));
        assertEquals(msg.getDirection(), dto.getDirection());
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
        when(request.getDirection()).thenReturn(false);
        when(request.getMessageID()).thenReturn(1);
        LocalDateTime dateTime = LocalDateTime.parse("2022-10-15T12:13");
        when(request.getTimestamp()).thenReturn(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime));

        ChatMessageDto dto = assertDoesNotThrow(() -> chatMessageMapper.requestToDto(request));

        assertEquals(request.getPostID(), dto.getPostID());
        assertEquals(request.getDirection(), dto.getDirection());
        assertEquals(request.getContent(), dto.getContent());
        assertEquals(dateTime, dto.getTimestamp());
        assertEquals(request.getMessageID(), dto.getMessageID());

    }

    @Test
    void testRequestToDto_nullInput() {
        ChatMessageDto msg = assertDoesNotThrow(() -> chatMessageMapper.requestToDto(null));
        assertNull(msg);
    }
}
