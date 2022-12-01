package com.bloodify.backend;

import com.bloodify.backend.dao.UserDAO;
import com.bloodify.backend.dao.UserDAOIFace;
import com.bloodify.backend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUserDAO {
    RandomGenerations random = new RandomGenerations();

    List<String> firstNames = new ArrayList<>();
    List<String> lastNames = new ArrayList<>();
    List<String> bloodTypes = new ArrayList<>();
    List<Character> bloodSigns = new ArrayList<>();
    List<String> emails = new ArrayList<>();
    List<String> passwords = new ArrayList<>();
    List<String> nationalIDs = new ArrayList<>();
    List<Boolean> haveDiseases = new ArrayList<>();
    List<LocalDate> lastDonates = new ArrayList<>();

    //  Inserting 100 values in the DB (randomly generated):
    @Autowired
    UserDAOIFace userDAO;

    public void insertValues() {
        int n = 100;
        for(int i=0; i<n; i++) {
            firstNames.add(random.generateName(4, 10));
            lastNames.add(random.generateName(4, 10));
            nationalIDs.add(random.generateNationalID());
            emails.add(random.generateEmail(10, 30));
            bloodTypes.add(random.generateBloodType());
            bloodSigns.add(random.generateBloodSign());
            haveDiseases.add(random.generateDiseases());
            lastDonates.add(random.generateDate(1980, 2022));
            passwords.add(random.generatePassword(15));
        }

        for(int i=0; i<n; i++) {
            User user = new User(
                    firstNames.get(i), lastNames.get(i), nationalIDs.get(i), emails.get(i), bloodTypes.get(i),
                    bloodSigns.get(i), haveDiseases.get(i), lastDonates.get(i), passwords.get(i)
            );
            userDAO.saveUser(user);
        }

    }

    public static void main(String[] args) {
        TestUserDAO test = new TestUserDAO();
        test.insertValues();
    }
}
