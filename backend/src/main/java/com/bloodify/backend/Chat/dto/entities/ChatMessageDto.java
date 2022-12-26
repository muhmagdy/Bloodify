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
    private Integer messageID;

    private Integer postID;

    private Integer donorID;

    private Integer senderID;
    
    private Integer recipientID;

    private String content;

    private LocalDateTime timestamp;
}
