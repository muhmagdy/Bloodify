package com.bloodify.backend.notification.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class NotificationHistoryKey implements Serializable{
    @Column(name = "postid")
    int postID;

    @Column(name = "userid")
    int userID;
    NotificationHistoryKey(){
        
    }
}
