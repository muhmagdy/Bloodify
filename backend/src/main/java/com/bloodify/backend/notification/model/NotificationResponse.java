package com.bloodify.backend.notification.model;

import java.time.LocalDateTime;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.model.entities.Post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponse {
    private String instituteName;
    private String acceptorName;
    private LocalDateTime lastTime;
    private double longitude;
    private double latitude;
    private int postID;

    public NotificationResponse(Post post) {
        Institution institution = post.getInstitution();
        User user = post.getUser();
        this.instituteName = institution.getName();
        this.acceptorName = user.getFirstName() + " " + user.getLastName();
        this.lastTime = post.getLastTime();
        this.latitude = institution.getLatitude();
        this.longitude = institution.getLongitude();
        this.postID = post.getPostID();
    }
}
