package com.bloodify.backend.Chat.model.entities;


import java.time.LocalDateTime;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.model.MessageStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@IdClass(ChatMessagePk.class)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int messageID;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn(name = "chatID")
    private Chat chat;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @Column(nullable = false)
    @JoinColumn(name = "userID")
    private User sender;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    @Column(nullable = false)
    @JoinColumn(name = "userID")
    private User recipient;

    //TODO: test for message length
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // @Enumerated(EnumType.STRING)
    // @Column(nullable = false)
    // private MessageStatus status;


    public ChatMessage(){}

}
