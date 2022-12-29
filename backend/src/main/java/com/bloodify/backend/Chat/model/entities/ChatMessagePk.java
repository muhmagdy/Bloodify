package com.bloodify.backend.Chat.model.entities;

import java.io.Serializable;

import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.PrimaryKeyJoinColumns;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ChatMessagePk implements Serializable {




    @ManyToOne(cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumns({ 
            @PrimaryKeyJoinColumn(name = "post_id", referencedColumnName = "post_id"),
            @PrimaryKeyJoinColumn(name = "donor_id", referencedColumnName = "userID") })
    private AcceptedPost acceptedPost;
}
