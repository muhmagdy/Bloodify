package com.bloodify.backend.Chat.model.entities;

import java.time.LocalDateTime;

import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
    /**
     * <p>The direction of the message,</p>
     * 
     * <p>true --> the sender is the post's owner.</p>
     * false --> the sender is the potential donor.
     */
    @Column(nullable = false)
    private Boolean direction;

    // TODO: test for message length
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public ChatMessage() {
    }

}
