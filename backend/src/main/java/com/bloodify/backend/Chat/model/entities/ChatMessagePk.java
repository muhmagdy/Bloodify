package com.bloodify.backend.Chat.model.entities;

import java.io.Serializable;

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
    private Chat chat;
    private Integer messageID;
    
}
