package com.bloodify.backend.UserRequests.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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

    Integer newMsgFor;
}
