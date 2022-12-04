package com.bloodify.backend.dao.interfaces;

import com.bloodify.backend.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.model.entities.User;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {

    @Autowired
    @Mock
    private UserDAO userDao;

    static int dataLength = 10;
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

    /***********   INSERT TESTS   ***********/
//  Testing normal insert
    @Test
    @Order(1)
    void saveUser1() {
        int n=0;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "AB-", isDisease[n], dates[n], passwords[n]))) ;
    }
    @Test
    @Order(2)
    void saveUser2() {
        int n=1;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "O+", isDisease[n], dates[n], passwords[n])));
    }
    @Test
    @Order(3)
    void saveUser3() {
        int n=2;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "AB-", isDisease[n], dates[n], passwords[n])));
    }
    @Test
    @Order(4)
    void saveUser4() {
        int n=3;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "A-", isDisease[n], dates[n], passwords[n])));
    }

//  Testing inserting a user with the same ID of an already inserted user
    @Test
    @Order(5)
    void saveRepeatedID() {
        int n=4;
        assertFalse(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[2], emails[n], bloodTypes[n], isDisease[n], dates[n], passwords[n])));
    }

//  Testing inserting a user with the same email of an already inserted user
    @Test
    @Order(6)
    void saveRepeatedEmail() {
        int n=5;
        assertFalse(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[1], emails[n], bloodTypes[n], isDisease[n], dates[n], passwords[n])));
    }

//  Testing inserting a user with repeated attributes except for ID and email
    @Test
    @Order(7)
    void saveRepeatedDataExceptUniques() {
        int n=2;
        assertTrue(userDao.saveUser(new User(
            fNames[n], lNames[n], IDs[9], emails[9], "A+", isDisease[n], dates[n], passwords[n])));
    }


    /***********   RETRIEVAL TESTS   ***********/
//  finding by EMAIL
    @Test
    @Order(8)
    void get1() {
        User user = userDao.findUserByEmail(emails[1]);
        assertEquals(user.getFirstName(), fNames[1]);
    }
    @Test
    @Order(8)
    void get2() {
        User user = userDao.findUserByEmail(emails[3]);
        assertEquals(user.getLastName(), lNames[3]);
    }
//  finding by NationalID
    @Test
    @Order(8)
    void get3() {
        User user = userDao.findUserByNationalID(IDs[0]);
        assertEquals(user.getFirstName(), fNames[0]);
    }
    @Test
    @Order(8)
    void get4() {
        User user = userDao.findUserByNationalID(IDs[2]);
        assertEquals(user.getLastTimeDonated(), dates[2]);
    }
//  finding all users matching blood type
    @Test
    @Order(8)
    void get5() {
        List<User> gotUsers = userDao.getUsersByBloodType("AB-");
        List<String> gotEmails = new ArrayList<>();
        for (User gotUser : gotUsers) {
            gotEmails.add(gotUser.getEmail());
        }
        List<String> actualEmails = new ArrayList<>();
        actualEmails.add(emails[0]);
        actualEmails.add(emails[2]);
        assertEquals(actualEmails, gotEmails);
    }
    @Test
    @Order(8)
    void get6() {
        List<User> gotUsers = userDao.getUsersByBloodType("O+");
        List<String> gotEmails = new ArrayList<>();
        for (User gotUser : gotUsers) {
            gotEmails.add(gotUser.getEmail());
        }
        List<String> actualEmails = new ArrayList<>();
        actualEmails.add(emails[1]);
        assertEquals(actualEmails, gotEmails);
    }
    @Test
    @Order(8)
    void get7() {
        List<User> gotUsers = userDao.getUsersByBloodType("B+");
        List<String> gotEmails = new ArrayList<>();
        for (User gotUser : gotUsers) {
            gotEmails.add(gotUser.getEmail());
        }
        assertEquals(0, gotEmails.size());
    }

    /****************   Matching email with password tests   **************/
    @Test
    @Order(8)
    void matchEmailAndPass1() {
        assertTrue(userDao.isUsernameAndPasswordMatching(emails[1], passwords[1]));
    }
    @Test
    @Order(8)
    void matchEmailAndPass2() {
        assertTrue(userDao.isUsernameAndPasswordMatching(emails[2], passwords[2]));
    }
    @Test
    @Order(8)
    void matchEmailAndPass3() {
        assertFalse(userDao.isUsernameAndPasswordMatching(emails[0], passwords[3]));
    }
}