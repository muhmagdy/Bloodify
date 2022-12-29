package com.bloodify.backend.Chat.model.entities;

import java.time.LocalDateTime;

import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
// import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
// @IdClass(ChatMessagePk.class)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Integer messageID;

    // @Id
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumns({ 
            @JoinColumn(name = "post_id", referencedColumnName = "post_postid"),
            @JoinColumn(name = "donor_id", referencedColumnName = "user_userid") })
    private AcceptedPost acceptedPost;


    // @Id
    // @Column(name = "message_id")
    // @GeneratedValue
    // private Integer messageID;

    // @EmbeddedId
    // @GeneratedValue
    // private ChatMessagePk pk;
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

    // public AcceptedPost getAcceptedPost() {
    //     return this.getPk().getAcceptedPost();
    // }
    // public Integer getMessageID() {
    //     return this.getPk().getMessageID();
    // }

}
