package com.bloodify.backend.Chat.model.entities;

import java.io.Serializable;

import com.bloodify.backend.UserRequests.model.entities.AcceptedPost;

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
public class ChatMessagePk implements Serializable {
    private Integer messageID;
    private AcceptedPost acceptedPost;
}
