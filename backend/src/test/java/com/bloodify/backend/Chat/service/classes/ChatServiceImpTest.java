package com.bloodify.backend.Chat.service.classes;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.dto.entities.ChatMessageDto;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.model.entities.ChatMessage;
import com.bloodify.backend.Chat.repository.interfaces.ChatMessageDao;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.exceptions.UserNotFoundException;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.AcceptRepository;

public class ChatServiceImpTest {

    ChatMessageDao msgDao;
    ChatMessageMapper msgMapper;

    UserDAO userDAO;

    AcceptRepository acceptRepository;

    ChatService service;

    ChatMessageDto chatMessageDto;
    List<ChatMessage> msgs;
    List<ChatMessageDto> msgsDto;

    User user;
    String email;
    Integer donorID, postID, userID;


    ChatMessage mockMsg(int i){
        ChatMessage msg  = Mockito.mock(ChatMessage.class);
        when(msg.getMessageID()).thenReturn(i);
        when(msg.getContent()).thenReturn(String.valueOf(i*234));
        when(msg.getDonorID()).thenReturn(donorID);
        when(msg.getPostOwnerID()).thenReturn(userID);
        return msg;
    }
    ChatMessageDto mockMsgDto(int i){
        ChatMessageDto msg  = Mockito.mock(ChatMessageDto.class);
        when(msg.getMessageID()).thenReturn(i);
        when(msg.getContent()).thenReturn(String.valueOf(i*234));
        return msg;
    }

    @BeforeEach
    void init(){
        msgDao = Mockito.mock(ChatMessageDao.class);
        msgMapper = Mockito.mock(ChatMessageMapper.class);
        userDAO = Mockito.mock(UserDAO.class);
        acceptRepository = Mockito.mock(AcceptRepository.class);


        user = Mockito.mock(User.class);
        email = "mock@email.com";
        donorID = 5;
        postID = 3;
        userID = 2;
        when(user.getEmail()).thenReturn(email);


        msgs = new LinkedList<>();
        msgsDto = new LinkedList<>();


        for(int i = 0; i < 5; i++){
            msgs.add(mockMsg(i));
            msgsDto.add(mockMsgDto(i));
        }
        chatMessageDto =  Mockito.mock(ChatMessageDto.class);
        service = new ChatServiceImp(userDAO, acceptRepository, msgDao, msgMapper);
    }
    
    @Test
    void testLoadChatMessages_happy() throws Exception {
        when(msgDao.findChatMessages(3, 5)).thenReturn(msgs);
        Iterator<ChatMessage> msgIter = msgs.iterator();
        Iterator<ChatMessageDto> dtoIter = msgsDto.iterator();

        when(user.getUserID()).thenReturn(userID);
        when(userDAO.findUserByEmail(email)).thenReturn(user);



        while(msgIter.hasNext() && dtoIter.hasNext()){
            when(msgMapper.entityToDto(msgIter.next())).thenReturn(dtoIter.next());
        }
        List<ChatMessageDto> actual = assertDoesNotThrow(() -> service.loadChatMessages(email, 3, 5));

        assertEquals(msgsDto, actual);

    }

    @Test
    void testLoadChatMessages_UserNotFound() throws Exception {
        when(msgDao.findChatMessages(3, 5)).thenReturn(msgs);
        Iterator<ChatMessage> msgIter = msgs.iterator();
        Iterator<ChatMessageDto> dtoIter = msgsDto.iterator();

        when(user.getUserID()).thenReturn(userID);
        when(userDAO.findUserByEmail(email)).thenReturn(null);



        while(msgIter.hasNext() && dtoIter.hasNext()){
            when(msgMapper.entityToDto(msgIter.next())).thenReturn(dtoIter.next());
        }
        assertThrows(UserNotFoundException.class,  () -> service.loadChatMessages(email, 3, 5));

    }

    @Test
    void testLoadChatMessages_noMessages() throws Exception {
        when(msgDao.findChatMessages(3, 5)).thenReturn(List.of());
        

        List<ChatMessageDto> actual = assertDoesNotThrow(() -> service.loadChatMessages(email, 3, 5));

        assertEquals(new LinkedList<ChatMessageDto>(), actual);
    }

    @Test
    void testSaveMessage_PostOwnerToDonor() throws Exception {

        AcceptedPost acceptedPost = Mockito.spy(AcceptedPost.class);

        Post post = Mockito.spy(Post.class);

        when(acceptedPost.getUser()).thenReturn(user);
        when(acceptedPost.getPost()).thenReturn(post);


        ChatMessage msg = Mockito.spy(ChatMessage.class);

        when(msgMapper.dtoToEntity(chatMessageDto)).thenReturn(msg);

        when(msg.getAcceptedPost()).thenReturn(acceptedPost);
        when(msg.getPostOwnerID()).thenReturn(userID);
        when(msg.getDonorID()).thenReturn(donorID);
        when(msg.getDirection()).thenReturn(true);

        when(msgDao.saveMessage(msg)).thenReturn(true);

        assertDoesNotThrow(() -> service.saveMessage(chatMessageDto));

        assertEquals(msg.getNewMsgFor(), donorID);
    }

    
    @Test
    void testSaveMessage_DonorToPostOwner() throws Exception {

        AcceptedPost acceptedPost = Mockito.spy(AcceptedPost.class);

        Post post = Mockito.spy(Post.class);

        when(acceptedPost.getUser()).thenReturn(user);
        when(acceptedPost.getPost()).thenReturn(post);


        ChatMessage msg = Mockito.spy(ChatMessage.class);

        when(msgMapper.dtoToEntity(chatMessageDto)).thenReturn(msg);

        when(msg.getAcceptedPost()).thenReturn(acceptedPost);
        when(msg.getPostOwnerID()).thenReturn(userID);
        when(msg.getDonorID()).thenReturn(donorID);
        when(msg.getDirection()).thenReturn(false);

        when(msgDao.saveMessage(msg)).thenReturn(true);

        assertDoesNotThrow(() -> service.saveMessage(chatMessageDto));

        assertEquals(msg.getNewMsgFor(), userID);
    }
}
