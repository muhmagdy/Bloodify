package com.bloodify.backend.Chat.controller.requests.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ChatRequest {
    int chatID;
    int postID;
    int donorID;
}
