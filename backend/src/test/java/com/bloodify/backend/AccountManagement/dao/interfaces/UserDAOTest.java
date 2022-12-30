package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.model.entities.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDAOTest {

    @Resource(name = "userDAOImp")
    private UserDAO userDao;

    @Resource()
    private UserRepository userRepository;

    static RandomUserGenerations random = new RandomUserGenerations();

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

        for (int i = 0; i < dataLength; i++) {
            fNames[i] = random.generateName(5, 10);
            lNames[i] = random.generateName(5, 10);
            IDs[i] = random.generateNationalID();
            emails[i] = random.generateEmail(20, 20);
            bloodTypes[i] = random.generateBloodType();
            isDisease[i] = random.generateDiseases();
            dates[i] = random.generateDate(1962, 2022);
            passwords[i] = random.generatePassword(15);
        }
        System.out.println(Arrays.toString(emails));
    }

    /***********   INSERT TESTS   ***********/
//  Testing normal insert
    @Test
    @Order(1)
    void saveUser1() {
        int n = 0;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "AB-", false, dates[n], passwords[n]))) ;
    }

    @Test
    @Order(1)
    void saveUser2() {
        int n = 1;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "O+", true, dates[n], passwords[n])));
    }

    @Test
    @Order(1)
    void saveUser3() {
        int n = 2;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "AB-", false, dates[n], passwords[n])));
    }

    @Test
    @Order(1)
    void saveUser4() {
        int n = 3;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[n], emails[n], "A-", true, null, passwords[n])));
    }

    //  Testing inserting a user with the same ID of an already inserted user
    @Test
    @Order(2)
    void saveRepeatedID() {
        reset();
        int n=4;
        assertFalse(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[2], emails[n], bloodTypes[n], isDisease[n], dates[n], passwords[n])));
    }

    //  Testing inserting a user with the same email of an already inserted user
    @Test
    @Order(2)
    void saveRepeatedEmail() {
        reset();
        int n=5;
        assertFalse(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[1], emails[n], bloodTypes[n], isDisease[n], dates[n], passwords[n])));
    }

    //  Testing entering null into nonNullable fields
//  If we set them null through setters, an error is erased before the assertFalse statement
    @Test
    @Order(2)
    void saveNullAttributes1() {
        reset();
        int n=6;
        assertFalse(userDao.saveUser(new User(
                fNames[n], null, IDs[1], emails[n], bloodTypes[n], isDisease[n], dates[n], passwords[n])));
    }

    @Test
    @Order(2)
    void saveNullAttributes2() {
        reset();
        int n=6;
        User user = new User(
                fNames[n], lNames[n], IDs[1], emails[n], null, isDisease[n], null, passwords[n]);
        assertFalse(userDao.saveUser(user));
    }


    //  Testing inserting a user with repeated attributes except for ID and email
    @Test
    @Order(2)
    void saveRepeatedDataExceptUniques() {
        int n = 2;
        assertTrue(userDao.saveUser(new User(
                fNames[n], lNames[n], IDs[9], emails[9], "A+", isDisease[n], dates[n], passwords[n])));
    }


    /***********   RETRIEVAL TESTS   ***********/
//  finding by EMAIL
    @Test
    @Order(4)
    void get1() {
        reset();
        User user = userDao.findUserByEmail(emails[1]);
        assertEquals(user.getFirstName(), fNames[1]);
    }

    @Test
    @Order(4)
    void get2() {
        reset();
        User user = userDao.findUserByEmail(emails[3]);
        assertEquals(user.getLastName(), lNames[3]);
    }

    //  finding by NationalID
    @Test
    @Order(4)
    void get3() {
        reset();
        User user = userDao.findUserByNationalID(IDs[0]);
        assertEquals(user.getFirstName(), fNames[0]);
    }

    @Test
    @Order(4)
    void get4() {
        reset();
        User user = userDao.findUserByNationalID(IDs[2]);
        assertEquals(user.getLastTimeDonated(), dates[2]);
    }
//  finding all users matching blood type
    void reset() {
        userRepository.deleteAll();
        saveUser1();
        saveUser2();
        saveUser3();
        saveUser4();
//        saveRepeatedDataExceptUniques();
    }

    @Test
    @Order(5)
    void get5() {
        reset();
        List<User> gotUsers = userDao.getUsersByBloodType("AB-");
        Set<String> gotEmails = new HashSet<>();
        for (User gotUser : gotUsers) {
            gotEmails.add(gotUser.getEmail());
        }
        Set<String> actualEmails = new HashSet<>();
        actualEmails.add(emails[0]);
        actualEmails.add(emails[2]);
        assertEquals(actualEmails, gotEmails);
    }

    @Test
    @Order(5)
    void get6() {
        reset();
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
    @Order(5)
    void get7() {
        reset();
        List<User> gotUsers = userDao.getUsersByBloodType("B+");
        List<String> gotEmails = new ArrayList<>();
        for (User gotUser : gotUsers) {
            gotEmails.add(gotUser.getEmail());
        }
        assertEquals(0, gotEmails.size());
    }
//    @Test
//    @Order(4)
//    void get8() {
//        List<User> gotUsers = userDao.getUsersByStatusAndDiseases(0, false);
//        assertEquals(2, gotUsers.size());
//    }

    /****************   Matching email with password tests   **************/
    @Test
    @Order(5)
    void matchEmailAndPass1() {
        assertTrue(userDao.isUsernameAndPasswordMatching(emails[1], passwords[1]));
    }

    @Test
    @Order(5)
    void matchEmailAndPass2() {
        assertTrue(userDao.isUsernameAndPasswordMatching(emails[2], passwords[2]));
    }

    @Test
    @Order(5)
    void matchEmailAndPass3() {
        assertFalse(userDao.isUsernameAndPasswordMatching(emails[0], passwords[3]));
    }


//  default value for longitude and latitude
    @Test
    @Order(6)
    void initialLongAndLang() {
        reset();
        assertNull(userDao.findUserByEmail(emails[1]).getLongitude());
    }

    @Test
    @Order(7)
    void ifUserEmail0Exist_thenReturnTrue() {
        assertTrue(userDao.isUserExistByEmail(emails[0]));
    }

    @Test
    @Order(7)
    void ifUserEmail1Exist_thenReturnTrue() {
        assertTrue(userDao.isUserExistByEmail(emails[1]));
    }

    @Test
    @Order(7)
    void ifUserEmail2Exist_thenReturnTrue() {
        assertTrue(userDao.isUserExistByEmail(emails[2]));
    }

    @Test
    @Order(7)
    void ifUserEmail3Exist_thenReturnTrue() {
        assertTrue(userDao.isUserExistByEmail(emails[3]));
    }

    @Test
    @Order(7)
    void ifUserEmail7Exist_thenReturnFalse() {
        assertFalse(userDao.isUserExistByEmail(emails[7]));
    }

    @Test
    @Order(7)
    void ifUserEmail8Exist_thenReturnFalse() {
        assertFalse(userDao.isUserExistByEmail(emails[8]));
    }

    @Test
    @Order(7)
    void updatePasswordUser0_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(userDao.updatePassword(emails[0], newPassword));
        assertEquals(newPassword, userDao.findUserByEmail(emails[0]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser1_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(userDao.updatePassword(emails[1], newPassword));
        assertEquals(newPassword, userDao.findUserByEmail(emails[1]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser2_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(userDao.updatePassword(emails[2], newPassword));
        assertEquals(newPassword, userDao.findUserByEmail(emails[2]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser7_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertFalse(userDao.updatePassword(emails[7], newPassword));
    }

    @Test
    @Order(7)
    void updatePasswordUser8_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertFalse(userDao.updatePassword(emails[8], newPassword));
    }








}
