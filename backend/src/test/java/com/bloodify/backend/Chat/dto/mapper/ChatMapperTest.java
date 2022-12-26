package com.bloodify.backend.Chat.dto.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.controller.requests.entities.ChatRequest;
import com.bloodify.backend.Chat.dto.entities.ChatDto;
import com.bloodify.backend.Chat.exceptions.DonorNotFoundException;
import com.bloodify.backend.Chat.model.entities.Chat;
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;

public class ChatMapperTest {

    PostDao postDao;
    UserDAO userDAO;

    Post post;
    User donor;

    ChatMapper chatMapper;

    @BeforeEach
    void init() {
        postDao = Mockito.mock(PostDao.class);
        userDAO = Mockito.mock(UserDAO.class);
        post = Mockito.mock(Post.class);
        donor = Mockito.mock(User.class);
        chatMapper = new ChatMapper(postDao, userDAO);
    }

    @Test
    void testDtoToEntity_happy() {
        ChatDto dto = Mockito.mock(ChatDto.class);
        when(dto.getChatID()).thenReturn(2);
        when(dto.getPostID()).thenReturn(3);
        when(dto.getDonorID()).thenReturn(5);

        when(postDao.getPostByID(3)).thenReturn(post);
        when(userDAO.findByID(5)).thenReturn(donor);

        Chat chat = assertDoesNotThrow(() -> chatMapper.dtoToEntity(dto));

        assertEquals(dto.getChatID(), chat.getChatID());
        assertTrue(chat.getPost().equals(post));
        assertTrue(chat.getDonor().equals(donor));

    }

    @Test
    void testDtoToEntity_nullInput() {
        Chat msg = assertDoesNotThrow(() -> chatMapper.dtoToEntity(null));
        assertNull(msg);
    }

    @Test
    void testDtoToEntity_postNotFound() {
        when(postDao.getPostByID(5)).thenReturn(null);
        ChatDto dto = Mockito.mock(ChatDto.class);
        when(dto.getChatID()).thenReturn(5);

        assertThrows(PostNotFoundException.class, () -> chatMapper.dtoToEntity(dto));


    }

    @Test
    void testDtoToEntity_donorNotFound() {
        when(postDao.getPostByID(5)).thenReturn(post);
        when(userDAO.findByID(1)).thenReturn(null);

        ChatDto dto = Mockito.mock(ChatDto.class);
        when(dto.getPostID()).thenReturn(5);
        when(dto.getDonorID()).thenReturn(1);


        assertThrows(DonorNotFoundException.class, () -> chatMapper.dtoToEntity(dto));

    }

    @Test
    void testDtoToRequest_happy() {
        ChatDto dto = Mockito.mock(ChatDto.class);
        when(dto.getChatID()).thenReturn(3);
        when(dto.getPostID()).thenReturn(2);
        when(dto.getDonorID()).thenReturn(5);

        ChatRequest request = assertDoesNotThrow(() -> chatMapper.dtoToRequest(dto));

        assertEquals(dto.getChatID(), request.getChatID());
        assertEquals(dto.getPostID(), request.getPostID());
        assertEquals(dto.getDonorID(), request.getDonorID());
    }

    @Test
    void testDtoToRequest_nullInput() {
        ChatRequest msg = assertDoesNotThrow(() -> chatMapper.dtoToRequest(null));
        assertNull(msg);
    }

    @Test
    void testEntityToDto() {
        Chat chat = Mockito.mock(Chat.class);
        when(chat.getChatID()).thenReturn(1);
        when(chat.getPost()).thenReturn(post);
        when(chat.getDonor()).thenReturn(donor);

        ChatDto dto = assertDoesNotThrow(() -> chatMapper.entityToDto(chat));

        assertEquals(dto.getChatID(), chat.getChatID());
        assertTrue(chat.getPost().equals(post));
        assertTrue(chat.getDonor().equals(donor));
    }

    @Test
    void testEntityToDto_nullInput() {
        ChatDto msg = assertDoesNotThrow(() -> chatMapper.entityToDto(null));
        assertNull(msg);

    }

    @Test
    void testRequestToDto() {
        ChatRequest request = Mockito.mock(ChatRequest.class);
        when(request.getChatID()).thenReturn(1);
        when(request.getPostID()).thenReturn(3);
        when(request.getDonorID()).thenReturn(5);

        ChatDto dto = assertDoesNotThrow(() -> chatMapper.requestToDto(request));

        assertEquals(request.getChatID(), dto.getChatID());
        assertEquals(request.getPostID(), dto.getPostID());
        assertEquals(request.getDonorID(), dto.getDonorID());

    }

    @Test
    void testRequestToDto_nullInput() {
        ChatDto msg = assertDoesNotThrow(() -> chatMapper.requestToDto(null));
        assertNull(msg);
    }
}
