package com.bloodify.backend.Chat.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@Getter
@IdClass(ChatMessagePk.class)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int messageID;
    @Id
    private int postID;
    @Id
    private int donorID;

    private int senderID, recipientID;
    private String content;
    private LocalDateTime timestamp;
    private MessageStatus status;

    
    public ChatMessage(int messageID, int postID, int donorID, int senderID, int recipientID, String content,
            LocalDateTime timestamp, MessageStatus status) {
        this.messageID = messageID;
        this.postID = postID;
        this.donorID = donorID;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.content = content;
        this.timestamp = timestamp;
        this.status = status;
    }
}
