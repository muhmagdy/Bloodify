package com.bloodify.backend.services.classes;


import com.bloodify.backend.Utils.TokenUtil;
import com.bloodify.backend.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.dao.interfaces.UserDAO;
import com.bloodify.backend.model.responses.LoginResponseBody;
import com.bloodify.backend.model.entities.Institution;
import com.bloodify.backend.model.entities.User;
import com.bloodify.backend.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.services.exceptions.EmailExistsException;
import com.bloodify.backend.services.exceptions.NationalIdExistsException;
import com.bloodify.backend.services.interfaces.AccountManagerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountManagerServiceImp implements AccountManagerService {
    @Autowired
    UserDAO userDAO;

    @Autowired 
    InstitutionDAO instDAO;

    @Autowired
    TokenUtil tokenUtil;

    @Override
    public LoginResponseBody userLogIn(Authentication auth) {
        try{
            String token = tokenUtil.generateToken(auth);
            User user = userDAO.findUserByEmail(auth.getName());
            return new LoginResponseBody(user, token);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public LoginResponseBody instLogIn(Authentication auth) {
        try{
            String token = tokenUtil.generateToken(auth);
            Institution inst = instDAO.findInstitutionByEmail(auth.getName());
            log.info("login inst");
            return new LoginResponseBody(inst, token);
        }catch (Exception e){
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean userSignUp(User user) {
        user.setPassword(new BCryptPasswordEncoder(10).encode(user.getPassword()));
        System.out.println(user.getPassword());
        boolean nationalIdExists = userDAO.findUserByNationalID(user.getNationalID()) != null;
        boolean emailExists = userDAO.findUserByEmail(user.getEmail()) != null;

        if (nationalIdExists) {
            if (emailExists)
                throw new BothEmailAndNationalIdExists();

            throw new NationalIdExistsException();
        } else if (emailExists)
            throw new EmailExistsException();

        return userDAO.saveUser(user);
    }

    @Override
    public boolean instSignUp(Institution institution) {
        institution.setPassword(new BCryptPasswordEncoder(10).encode(institution.getPassword()));
        boolean emailExists = instDAO.findInstitutionByEmail(institution.getEmail()) != null;
        if (emailExists)
            throw new EmailExistsException();
        return instDAO.saveInstitution(institution);
    }

    @Override
    public void userSignOut(Authentication auth) {
    }

    @Override
    public void instSignOut(Authentication auth) {
    }


}
