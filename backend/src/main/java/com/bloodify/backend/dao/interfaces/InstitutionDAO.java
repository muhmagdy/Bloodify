package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.model.entities.Institution;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface InstitutionDAO extends UserDetailsService{
    public boolean saveInstitution(Institution newInst);

    public Institution findInstitutionByEmail(String email);

    public List<Institution> haveBloodPackets(String bloodType, int quantity);

//    public void setChangedPacketCount(List<String> changes, Institution institution);
//    public void setBloodPacketChanges(String bloodType, int newCount, String email);
}
