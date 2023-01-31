package com.bloodify.backend.AccountManagement.services.classes;

import com.bloodify.backend.AccountManagement.dao.interfaces.PasswordResetRepository;
import com.bloodify.backend.AccountManagement.model.entities.PasswordReset;
import com.bloodify.backend.AccountManagement.model.responses.SignUpResponse;
import com.bloodify.backend.AccountManagement.services.exceptions.BothEmailAndNationalIdExists;
import com.bloodify.backend.AccountManagement.services.exceptions.NationalIdExistsException;
import com.bloodify.backend.AccountManagement.services.interfaces.AccountManagerService;
import com.bloodify.backend.AccountManagement.Utils.TokenUtil;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.LoginSessionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.responses.LoginResponseBody;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.LoginSession;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.AccountManagement.services.exceptions.EmailExistsException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
public class AccountManagerServiceImp implements AccountManagerService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    InstitutionDAO instDAO;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EncoderService encoderService;
    @Autowired
    LoginSessionDAO loginSessionDAO;

    @Autowired
    PasswordResetRepository passwordResetRepository;

    @Autowired
    EmailService emailService;

    @Override
    public LoginResponseBody userLogIn(Authentication auth, String mobileToken) {
        try {
            String token = tokenUtil.generateToken(auth);
            User user = userDAO.findUserByEmail(auth.getName());
            try {
                String loginToken = loginSessionDAO.getToken(user.getEmail());
                loginSessionDAO.updateToken(user.getEmail(), mobileToken);
            }catch (Exception e){
                LoginSession loginSession = new LoginSession(user.getEmail(), mobileToken);
                loginSessionDAO.save(loginSession);
            }
            return new LoginResponseBody(user, token);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public LoginResponseBody instLogIn(Authentication auth) {
        try {
            String token = tokenUtil.generateToken(auth);
            Institution inst = instDAO.findInstitutionByEmail(auth.getName());
            log.info("login inst");
            return new LoginResponseBody(inst, token);
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean userSignUp(User user) {
        user.setPassword(encoderService.encode(user.getPassword()));
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
        institution.setPassword(encoderService.encode(institution.getPassword()));
        boolean emailExists = instDAO.findInstitutionByEmail(institution.getEmail()) != null;
        if (emailExists)
            throw new EmailExistsException();
        return instDAO.saveInstitution(institution);
    }

    @Override
    public boolean userSignOut(Authentication auth) {
        try {
            System.out.println(auth.getName());
            return loginSessionDAO.delete(auth.getName());

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void instSignOut(Authentication auth) {
    }

    @Override
    public boolean updateHasDiseases(boolean hasDiseases, String email) {
        return userDAO.updateHasDiseases(hasDiseases, email) != 0;
    }

    public SignUpResponse sendVerificationCode(String email) {
        if (!userDAO.isUserExistByEmail(email) && !instDAO.isInstitutionExistByEmail(email))
            return new SignUpResponse(false, "Cannot send code.");

        PasswordReset passwordReset = new PasswordReset(
                email, generateCode(), LocalDateTime.now());
        try {
            passwordResetRepository.deleteByEmail(email);
            passwordResetRepository.save(passwordReset);
            emailService.sendPasswordResetEmail(email, passwordReset.getCode());
            System.out.println("Email sent successfully");
            return new SignUpResponse(true, "Code sent successfully to " + email);
        } catch (Exception e) {
            System.out.println("Hello from exception");
            e.printStackTrace();
            return new SignUpResponse(false, "Cannot send code. Please try again later.");
        }
    }

    @Override
    public boolean resetPassword(String email, String code, String newPassword) {
        PasswordReset passwordReset = passwordResetRepository.findByEmail(email);
        if (passwordReset == null)
            return false;
        if (!passwordReset.getCode().equals(code))
            return false;
        if (passwordReset.getCreatedAt().isBefore(LocalDateTime.now().minusMinutes(30)))
            return false;
        String encodedPassword = encoderService.encode(newPassword);
        if (userDAO.updatePassword(email, encodedPassword) || instDAO.updatePassword(email, encodedPassword)) {
            passwordResetRepository.deleteByEmail(email);
            return true;
        }
        return false;
    }

    @Scheduled(fixedRate = 1800000)
    private void deleteExpiredCodes() {
        passwordResetRepository.removeOlderThan(
                LocalDateTime.now().minusMinutes(30));
    }

    private String generateCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(1000, 10000));
    }

}
