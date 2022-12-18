package com.bloodify.backend.UserRequests.model.entities;

import com.bloodify.backend.AccountManagement.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AcceptPKId implements Serializable {
    private Post post;
    private User user;
}
