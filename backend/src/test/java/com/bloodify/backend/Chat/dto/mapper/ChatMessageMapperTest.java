package com.bloodify.backend.Chat.dto.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.Chat.repository.interfaces.ChatDao;

public class ChatMessageMapperTest {
    ChatDao chatDao;
    UserDAO userDAO;

    ChatMessageMapper chatMapper;

    @BeforeEach
    void init(){
        chatDao = Mockito.mock(ChatDao.class);
        userDAO = Mockito.mock(UserDAO.class);
        chatMapper = new ChatMessageMapper(chatDao, userDAO);
    }
    @Test
    void testDtoToEntity_happy() {
        //TODO

    }

    @Test
    void testDtoToEntity_nullInput() {
        //TODO
    }

    @Test
    void testDtoToEntity_nullPost() {
        //TODO

    }

    @Test
    void testDtoToEntity_postNotFound() {
        //TODO

    }

    @Test
    void testDtoToEntity_donorNotFound() {
        //TODO

    }

    @Test
    void testDtoToEntity_nullDonor() {
        //TODO

    }


    @Test
    void testDtoToRequest_happy() {
    }

    @Test
    void testDtoToRequest_nullInput() {
        //TODO
    }


    @Test
    void testEntityToDto() {
        //TODO
    }

    @Test
    void testEntityToDto_nullInput() {
        //TODO

    }

    @Test
    void testRequestToDto() {
        //TODO

    }

    @Test
    void testRequestToDto_nullInput() {
        //TODO
    }
}
