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

        Double long1 = 29.882137;   Double lat1 = 31.210453;    // represents Kayet Bay carrefour with elgeich road
        Double long2 = 29.885613;   Double lat2 = 31.213481;    // represents Kayet Bay castle itself
        Double long3 = 29.894327;   Double lat3 = 31.199945;    // represents France Consulate
        Double long4 = 29.933361;   Double lat4 = 31.217912;    // represents Sporting Club Sporting gate
        Double instLong = 29.892540;    Double instLat = 31.197730;    // represents Andalusia Al Shalalat Hospital

        userDao.updateLongitudeAndLatitude(userDao.findUserByEmail(emails[0]).getUserID(), long1, lat1);
        userDao.updateLongitudeAndLatitude(userDao.findUserByEmail(emails[1]).getUserID(), long2, lat2);
        userDao.updateLongitudeAndLatitude(userDao.findUserByEmail(emails[2]).getUserID(), long3, lat3);
        userDao.updateLongitudeAndLatitude(userDao.findUserByEmail(emails[3]).getUserID(), long4, lat4);
    }

    RandomUserGenerations random = new RandomUserGenerations();

    @Test
    @Order(2)
    void getUsersToBeNotified() {

    }
}