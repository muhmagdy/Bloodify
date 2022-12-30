package com.bloodify.backend.AccountManagement.dao.classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionRepository;
import com.bloodify.backend.AccountManagement.model.entities.LoginSession;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginSessionDAOImp implements LoginSessionDAO {
    @Autowired
    @Qualifier("LoginSessionRepository")
    LoginSessionRepository loginSessionRepository;

    @Override
    public String getToken(String email) {
        LoginSession loginSession = loginSessionRepository.findTokenByEmail(email);

        if (loginSession == null) {
            return null;
        }
        return loginSession.getToken();
    }

    @Override
    public boolean delete(String email) {
        try {
            if (loginSessionRepository.deleteByEmail(email) == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(LoginSession login) {
        try {
            loginSessionRepository.save(login);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateToken(String email, String token) {
        loginSessionRepository.updateToken(email, token);
    }

}
