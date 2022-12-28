package com.bloodify.backend.AccountManagement.dao.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bloodify.backend.AccountManagement.model.entities.LoginSession;
import com.google.firebase.internal.NonNull;

import jakarta.transaction.Transactional;

@Repository("LoginSessionRepository")

public interface LoginSessionRepository extends JpaRepository<LoginSession, Integer> {
        LoginSession findTokenByEmail(String email);

        void deleteSessionByEmail(String email);

        @Transactional
        @Modifying(clearAutomatically = true, flushAutomatically = true)
        @Query(value = "UPDATE login_session " +
                        "SET token = :currentToken " +
                        "WHERE email = :email", nativeQuery = true)
        void updateToken(@Param("email") String email,
                        @Param("currentToken") String currentToken);
}
