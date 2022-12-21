package com.bloodify.backend.UserRequests.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@IdClass(AcceptPKId.class)
public class AcceptedPost {
    @Id
    @ManyToOne
    Post post;
    @Id
    @ManyToOne
    User user;

    Double longitude;
    Double latitude;
    Double threshold;
}
