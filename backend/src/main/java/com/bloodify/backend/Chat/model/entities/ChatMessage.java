package com.bloodify.backend.Chat.model.entities;

import java.time.LocalDateTime;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Chat.model.MessageStatus;
import com.bloodify.backend.UserRequests.model.entities.AcceptPKId;
import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import com.bloodify.backend.UserRequests.model.entities.Post;

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
import jakarta.persistence.PrimaryKeyJoinColumns;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageID;

    @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumns({ 
            @PrimaryKeyJoinColumn(name = "post_id", referencedColumnName = "post_id"),
            @PrimaryKeyJoinColumn(name = "donor_id", referencedColumnName = "userID") })
    private AcceptedPost acceptedPost;

    // @Id
    // @ManyToOne(cascade = CascadeType.REMOVE)
    // @PrimaryKeyJoinColumn(name = "post_id", referencedColumnName = "post_id")
    // private Post post;

    // @Id
    // @ManyToOne(cascade = CascadeType.REMOVE)
    // @PrimaryKeyJoinColumn(name = "donor_id", referencedColumnName = "userID")
    // private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_ID", referencedColumnName = "userID", nullable = false)
    private User sender;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "recipient_ID", referencedColumnName = "userID", nullable = false)
    private User recipient;

    // TODO: test for message length
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MessageStatus status;

    public ChatMessage() {
    }

}