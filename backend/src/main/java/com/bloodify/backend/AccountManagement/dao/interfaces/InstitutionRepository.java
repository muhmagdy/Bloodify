package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.model.entities.Institution;
//import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.UserRequests.controller.request.entity.InstitutionBrief;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InstitutionRepository")
public interface InstitutionRepository extends JpaRepository<Institution, Integer>,
        JpaSpecificationExecutor<Institution> {
    List<Institution> findByEmail(String email);


    List<Institution> findAll();
    List<Institution> findAll(Specification<Institution> spec);
    List<Institution> findByInstitutionID(int institutionID);
    List<Institution> haveBloodPacketsPositiveA(int quantity);
    List<Institution> haveBloodPacketsPositiveB(int quantity);
    List<Institution> haveBloodPacketsPositiveAB(int quantity);
    List<Institution> haveBloodPacketsPositiveO(int quantity);
    List<Institution> haveBloodPacketsNegativeA(int quantity);
    List<Institution> haveBloodPacketsNegativeB(int quantity);
    List<Institution> haveBloodPacketsNegativeAB(int quantity);
    List<Institution> haveBloodPacketsNegativeO(int quantity);

//    void setPacketChangesPositiveA(int count, String email);
//    void setPacketChangesPositiveB(int count, String email);
//    void setPacketChangesPositiveAB(int count, String email);
//    void setPacketChangesPositiveO(int count, String email);
//    void setPacketChangesNegativeA(int count, String email);
//    void setPacketChangesNegativeB(int count, String email);
//    void setPacketChangesNegativeAB(int count, String email);
//    void setPacketChangesNegativeO(int count, String email);

//    void setBloodPacketChanges(String type, int newCount, String email);
}
