package com.bloodify.backend.AccountManagement.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordReset {
    @Id
    @Column(nullable = false, length = 60)
    private String email;

    @Column(nullable = false, length = 4)
    private String code;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}
