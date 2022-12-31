package com.bloodify.backend.notification.model;

import org.hibernate.annotations.NamedNativeQuery;

import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@Entity
@AllArgsConstructor
// @NamedNativeQuery(
//     name="NotificationHistory.findPostsbyUser", 
//     query = "slect NotificationHistory from User as u,Post as p,Notification_History as n"+
//     " Where n.postid=p.postid and n.userid=u.userid and u.userid = :param")
public class NotificationHistory {
    @EmbeddedId
    NotificationHistoryKey id;
    @ManyToOne
    @MapsId("userID")  
     @JoinColumn(name = "userid")
     private User user;
     @ManyToOne
     @MapsId("postid")
     @JoinColumn(name = "postid")
     private Post post;
     NotificationHistory(){
        
     }
}
