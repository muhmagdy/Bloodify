package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.PasswordReset;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

public interface PasswordResetRepository extends JpaRepository<PasswordReset, String> {
    PasswordReset findByEmail(String email);

    boolean existsByEmail(String email);

    @Transactional
    long deleteByEmail(@NonNull String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM PasswordReset m WHERE m.createdAt < :timestamp")
    int removeOlderThan(@Param("timestamp") LocalDateTime timestamp);

}