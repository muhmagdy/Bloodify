package com.bloodify.backend.Chat.controller.requests.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    int chatID, messageID, senderID, recipientID;
    String content;
    LocalDateTime timestamp;
}
