package com.bloodify.backend.UserRequests.service.interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {
    @Resource(name = "userDAOImp")
    private UserDAO userDao;
    @Autowired
    private PostService postService;

    static int dataLength = 4;
    static String[] fNames = new String[dataLength];
    static String[] lNames = new String[dataLength];
    static String[] IDs = new String[dataLength];
    static String[] emails = new String[dataLength];
    static String[] bloodTypes = new String[dataLength];
    static boolean[] isDisease = new boolean[dataLength];
    static LocalDate[] dates = new LocalDate[dataLength];
    static String[] passwords = new String[dataLength];

    @BeforeAll
    public static void setters() {
        RandomUserGenerations random = new RandomUserGenerations();
        for(int i=0; i<dataLength; i++) {
            fNames[i] = random.generateName(5, 10);
            lNames[i] = random.generateName(5, 10);
            IDs[i] = random.generateNationalID();
            emails[i] = random.generateEmail(8, 20);
            bloodTypes[i] = random.generateBloodType();
            isDisease[i] = random.generateDiseases();
            dates[i] = random.generateDate(1962, 2022);
            passwords[i] = random.generatePassword(15);
        }
    }

    /***********   Preparing Tests   ***********/
    @Order(1)
    void saveUsers() {
        int n;
        for(n=0; n<4; n++)
            userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "AB-", isDisease[n], dates[n], passwords[n])) ;


    }

    RandomUserGenerations random = new RandomUserGenerations();

    @Test
    @Order(2)
    void getUsersToBeNotified() {
            /*
             * TO DO 
             */
    }
}