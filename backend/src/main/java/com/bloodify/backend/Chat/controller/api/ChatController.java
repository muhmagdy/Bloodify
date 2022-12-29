package com.bloodify.backend.Chat.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bloodify.backend.Chat.controller.requests.entities.ChatMessageRequest;
import com.bloodify.backend.Chat.dto.mapper.ChatMessageMapper;
import com.bloodify.backend.Chat.service.interfaces.ChatService;
import com.bloodify.backend.UserRequests.exceptions.AcceptedPostNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/v1/user/chat")
public class ChatController {

    final private ChatService chatService;
    final private ChatMessageMapper mapper;


    ChatController(ChatService chatService, ChatMessageMapper mapper){
        this.chatService = chatService;
        this.mapper = mapper;
    }


    @GetMapping("/messages")
    ResponseEntity<List<ChatMessageRequest>> messages(@RequestParam Integer postID, @RequestParam Integer donorID){
        log.info("postID: " + postID.toString());
        log.info("donorID: " + donorID.toString());
        return ResponseEntity.ok().body(this.chatService.loadChatMessages(postID, donorID)
                .stream()
                .map((dto) -> this.mapper.dtoToRequest(dto))
                .toList());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AcceptedPostNotFoundException.class)
    ResponseEntity<String> exceptionHandler(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
