package com.bloodify.backend.Chat.dto.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private int messageID;

    private int chatID;

    private int senderID;
    
    private int recipientID;

    private String content;

    private LocalDateTime timestamp;
}
