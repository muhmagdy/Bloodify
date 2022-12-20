package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface InstitutionDAO extends UserDetailsService{
    public boolean saveInstitution(Institution newInst);

    public Institution findInstitutionByEmail(String email);

    public List<Institution> haveBloodPackets(String bloodType, int quantity);

    int getBagsCount(String email, String bloodType);

    int updateBagsCount(String email, String bloodType, Integer newBagsCount);

    int incrementBagsCountBy(String email, String bloodType, Integer addedBagsCount);

}
