package com.bloodify.backend.AccountManagement.dao.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloodify.backend.AccountManagement.model.entities.LoginSession;

public interface LoginSessionDAO   {
    String getToken(String email);
    boolean delete(String email);
    boolean save(LoginSession login);
    void updateToken(String email,String token);
}
