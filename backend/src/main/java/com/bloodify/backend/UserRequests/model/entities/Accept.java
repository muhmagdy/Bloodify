package com.bloodify.backend.UserRequests.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@IdClass(AcceptPKId.class)
public class Accept {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
