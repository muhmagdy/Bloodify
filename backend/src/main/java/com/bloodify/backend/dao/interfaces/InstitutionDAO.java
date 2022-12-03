package com.bloodify.backend.dao.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bloodify.backend.model.entities.Institution;

public interface InstitutionDAO extends UserDetailsService{
    Institution findUserByEmail(String email);
}
