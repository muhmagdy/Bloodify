package com.bloodify.backend.AccountManagement.model.entities;

import com.google.firebase.internal.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@NamedNativeQuery(
        name = "login_session.findTokenByEmail",
        query = "SELECT * FROM LoginSession WHERE email = ?",
        resultClass = LoginSession.class
)
@NamedNativeQuery(
        name = "login_session.deleteSessionByEmail",
        query = "DELETE FROM LoginSession WHERE email = ?"
)
@AllArgsConstructor
public class LoginSession {
    @Id
    @NonNull
    @Size(max = 40, message = "Email is too long")
    @Column(unique = true, nullable = false, length = 40)
    private String email;
    @NonNull
    @Size(max = 400, message = "Token is too long")
    @Column(unique = true, nullable = false, length = 400)
    private String token;
}
