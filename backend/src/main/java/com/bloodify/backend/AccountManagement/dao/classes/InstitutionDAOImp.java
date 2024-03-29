package com.bloodify.backend.AccountManagement.dao.classes;

import java.util.List;

// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.model.authentication.InstitutionAuthentication;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;


/**
 * Required Services:
 * at sign in --> find institution with email 'bla', match username with password
 * at sign up --> check that username is not repeated, insert new user (saveUser)
 * get institutions having blood packets of type 'bla' and for a quantity of 'blabla'
 */

@Slf4j
@Service
public class InstitutionDAOImp implements InstitutionDAO {
    @Autowired
    @Qualifier("InstitutionRepository")
    InstitutionRepository instRepo;

    public boolean saveInstitution(Institution newInst) {
        try {
            instRepo.save(newInst);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Institution findInstitutionByEmail(String email) {

        List<Institution> foundInstitutions = instRepo.findByEmail(email);

        if (foundInstitutions.isEmpty())
            return null;
        else
            return foundInstitutions.get(0);
    }

    public Institution findInstitutionByID(int ID){
        List<Institution> foundInstitutions = instRepo.findByInstitutionID(ID);
        if (foundInstitutions.isEmpty())
            return null;
        else
            return foundInstitutions.get(0);
    }

    public List<Institution> haveBloodPackets(String bloodType, int quantity) {
        return switch (bloodType) {
            case "A+" -> instRepo.haveBloodPacketsPositiveA(quantity);
            case "B+" -> instRepo.haveBloodPacketsPositiveB(quantity);
            case "AB+" -> instRepo.haveBloodPacketsPositiveAB(quantity);
            case "O+" -> instRepo.haveBloodPacketsPositiveO(quantity);
            case "A-" -> instRepo.haveBloodPacketsNegativeA(quantity);
            case "B-" -> instRepo.haveBloodPacketsNegativeB(quantity);
            case "AB-" -> instRepo.haveBloodPacketsNegativeAB(quantity);
            case "O-" -> instRepo.haveBloodPacketsNegativeO(quantity);
            default -> new ArrayList<>();
        };
    }

    @Override
    public int getBagsCount(String email, String bloodType) {
        Institution institution = findInstitutionByEmail(email);
        return switch (bloodType) {
            case "A+" -> institution.getPositiveA_bagsCount();
            case "B+" -> institution.getPositiveB_bagsCount();
            case "AB+" -> institution.getPositiveAB_bagsCount();
            case "O+" -> institution.getPositiveO_bagsCount();
            case "A-" -> institution.getNegativeA_bagsCount();
            case "B-" -> institution.getNegativeB_bagsCount();
            case "AB-" -> institution.getNegativeAB_bagsCount();
            case "O-" -> institution.getNegativeO_bagsCount();
            default -> -1;
        };
    }

    @Override
    public int updateBagsCount(String email, String bloodType, Integer newBagsCount) {
        return switch (bloodType) {
            case "A+" -> instRepo.updatePositiveA_bagsCountByEmail(newBagsCount, email);
            case "B+" -> instRepo.updatePositiveB_bagsCountByEmail(newBagsCount, email);
            case "AB+" -> instRepo.updatePositiveAB_bagsCountByEmail(newBagsCount, email);
            case "O+" -> instRepo.updatePositiveO_bagsCountByEmail(newBagsCount, email);
            case "A-" -> instRepo.updateNegativeA_bagsCountByEmail(newBagsCount, email);
            case "B-" -> instRepo.updateNegativeB_bagsCountByEmail(newBagsCount, email);
            case "AB-" -> instRepo.updateNegativeAB_bagsCountByEmail(newBagsCount, email);
            case "O-" -> instRepo.updateNegativeO_bagsCountByEmail(newBagsCount, email);
            default -> -1;
        };
    }

    @Override
    public int incrementBagsCountBy(String email, String bloodType, Integer addedBagsCount) {
        return switch (bloodType) {
            case "A+" -> instRepo.incrementAPosBagsCountBy(addedBagsCount, email);
            case "B+" -> instRepo.incrementBPosBagsCountBy(addedBagsCount, email);
            case "AB+" -> instRepo.incrementABPosBagsCountBy(addedBagsCount, email);
            case "O+" -> instRepo.incrementOPosBagsCountBy(addedBagsCount, email);
            case "A-" -> instRepo.incrementANegBagsCountBy(addedBagsCount, email);
            case "B-" -> instRepo.incrementBNegBagsCountBy(addedBagsCount, email);
            case "AB-" -> instRepo.incrementABNegBagsCountBy(addedBagsCount, email);
            case "O-" -> instRepo.incrementONegBagsCountBy(addedBagsCount, email);
            default -> -1;
        };
    }

    @Override
    public boolean isInstitutionExistByEmail(String email) {
        return instRepo.existsByEmail(email);
    }

    @Override
    public boolean updatePassword(String email, String newPassword) {
        return instRepo.updatePasswordByEmail(newPassword, email) == 1;
    }

//    public void setChangedPacketCount(List<String> changes, Institution institution) {
//        for(String change: changes) {
//            switch (change) {
//                case "An":
//                    instRepo.setPacketChangesNegativeA(institution.getNegativeA_bagsCount(), institution.getEmail());
//                    break;
//                case "Ap":
//                    instRepo.setPacketChangesPositiveA(institution.getPositiveA_bagsCount(), institution.getEmail());
//                    break;
//                case "Bn":
//                    instRepo.setPacketChangesNegativeB(institution.getNegativeB_bagsCount(), institution.getEmail());
//                    break;
//                case "Bp":
//                    instRepo.setPacketChangesPositiveB(institution.getPositiveB_bagsCount(), institution.getEmail());
//                    break;
//                case "ABn":
//                    instRepo.setPacketChangesNegativeAB(institution.getNegativeAB_bagsCount(), institution.getEmail());
//                    break;
//                case "ABp":
//                    instRepo.setPacketChangesPositiveAB(institution.getPositiveAB_bagsCount(), institution.getEmail());
//                    break;
//                case "On":
//                    instRepo.setPacketChangesNegativeO(institution.getNegativeO_bagsCount(), institution.getEmail());
//                    break;
//                case "Op":
//                    instRepo.setPacketChangesPositiveO(institution.getPositiveO_bagsCount(), institution.getEmail());
//                    break;
//                default:    break;
//            }
//        }
//    }

//    public void setBloodPacketChanges(String bloodType, int newCount, String email) {
//        instRepo.setBloodPacketChanges(bloodType, newCount, email);
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + " inst");
        Institution institution = this.findInstitutionByEmail(username);
        if(institution == null)    throw new UsernameNotFoundException(username + " not found");
        log.info(institution.getEmail());
        // if(!username.equals("foo")) throw new UsernameNotFoundException(username + " not found");
        InstitutionAuthentication userAuth = new InstitutionAuthentication(institution);
        return userAuth;
        // return new User("foo", "foo", List.of(new SimpleGrantedAuthority("Institution")));
    }

}
