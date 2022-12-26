package com.bloodify.backend.Chat.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
import com.bloodify.backend.UserRequests.exceptions.PostNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
// @RequestMapping("/chat")
// @MessageMapping("/chat")
public class ChatController {

    private ChatService chatService;
    private ChatMessageMapper mapper;

    private SimpMessagingTemplate messageingTemplate;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @MessageMapping("/test")
    // @SendTo("/app/v1/user/chat/send")
    public void processMessage(@Payload ChatMessageRequest chatMessageRequest) throws Exception{
        // log.info(chatMessageRequest.toString());
        System.out.println("===========================");
        System.out.println(chatMessageRequest.getContent());
        this.chatService.saveMessage(mapper.requestToDto(chatMessageRequest));
        // messageingTemplate.convertAndSendToUser(
        //         chatMessageRequest.getRecipientID().toString(), "/queue/messages",
        //         "You may have new messages."
        // );
        
    }


    @GetMapping("/chat/messages")
    ResponseEntity<List<ChatMessageRequest>> messages(@RequestParam Integer postID, @RequestParam Integer donorID){
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
