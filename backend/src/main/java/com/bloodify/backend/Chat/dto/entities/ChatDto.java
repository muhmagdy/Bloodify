package com.bloodify.backend.Chat.dto.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatDto {

    private int chatID;

    private int postID;

    private int donorID;

    public ChatDto(int chatID, int postID, int donorID) {
        this.chatID = chatID;
        this.postID = postID;
        this.donorID = donorID;
    }
}
