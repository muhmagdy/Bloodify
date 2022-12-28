package com.bloodify.backend.Chat.controller.requests.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageRequest {
    private Integer messageID, postID, donorID;
    private Boolean direction;
    private String content;
    private String timestamp;
}
