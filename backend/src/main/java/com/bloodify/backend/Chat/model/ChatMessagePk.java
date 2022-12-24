package com.bloodify.backend.Chat.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode
public class ChatMessagePk implements Serializable {
    private int postID, messageID, donorID;

}
