package com.bloodify.backend.AccountManagement.dao.interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InstitutionDAOTest {

//    @Autowired
    @Resource(name = "institutionDAOImp")
    @Mock
    private InstitutionDAO instDao;

    static RandomUserGenerations random = new RandomUserGenerations();

    static int dataLength = 10;
    static String[] emails = new String[dataLength];
    static String[] names = new String[dataLength];
    static String[] passwords = new String[dataLength];
    static String[] locations = new String[dataLength];
    static int[] workingHours = new int[dataLength];
    static int[][] bloodCounts = new int[dataLength][8];


    @BeforeAll
    public static void setters() {

        for(int i=0; i<dataLength; i++) {
            emails[i] = random.generateEmail(10,30);
            names[i] = random.generateName(5, 10);
            passwords[i] = random.generatePassword(20);
            for(int j=0; j<8; j++) {
                bloodCounts[i][j] = random.generateCount(0, 50);
            }
            locations[i] = random.generateName(5,20);
        }
    }

//  Legal Statement
    @Test
    @Order(1)
    void saveInstitution1() {
        int n=0;
        assertTrue(instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[n], locations[n], workingHours[n]
        ))) ;
        instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[n], locations[n], workingHours[n]
        ));
    }

//  Legal Statement
    @Test
    @Order(1)
    void saveInstitution2() {
        int n=1;
        assertTrue(instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[n], locations[n], workingHours[n]
        ))) ;
    }

//  Legal Statement
    @Test
    @Order(1)
    void saveInstitution3() {
        int n=2;
        assertTrue(instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[n], locations[n], workingHours[n]
        ))) ;
    }

//  Legal Statement, location has not a not null constraint
    @Test
    @Order(1)
    void saveInstitution4() {
        int n=3;
        assertFalse(instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[n], null, workingHours[n]
        ))); ;
    }

//  Email is repeated
    @Test
    @Order(2)
    void saveInstitution5() {
        int n=4;
        assertFalse(instDao.saveInstitution(new Institution(
                emails[2], names[n], passwords[n], locations[n], workingHours[n]
        ))) ;
    }

    @Test
    @Order(2)
    void saveInstitution6() {
        int n=6;
        Institution institution = new Institution(emails[n], null, passwords[n], locations[n], workingHours[n]);
        assertFalse(instDao.saveInstitution(institution)) ;
    }

//  All data are repeated except email
    @Test
    @Order(3)
    void saveInstitution7() {
        int n=5;
        assertTrue(instDao.saveInstitution(new Institution(
                emails[n], names[n], passwords[1], locations[1], workingHours[1]
        ))) ;
    }

/*********   Setting blood packet counts for each institution in our DB (5 institutions now)
 * & testing the findByEmail method by asserting not null ********/

//  Here we set only -ve blood types, so +ve ones are all 0
    @Test
    @Order(4)
    void setPackets1() {
        Institution institution = instDao.findInstitutionByEmail(emails[0]);
        assertNotNull(institution);
//        int minCount = 1;
//        int maxCount = 10;
        institution.setNegativeA_bagsCount(2);
        institution.setNegativeB_bagsCount(4);
        institution.setNegativeAB_bagsCount(6);
        institution.setNegativeO_bagsCount(8);

//        List<String> changes = new ArrayList<>();
//        changes.add("An");  changes.add("Bn");  changes.add("ABn"); changes.add("On");
//        instDao.setChangedPacketCount(changes, institution);
    }

//  Here we set only +ve blood types, so -ve ones are all 0
    @Test
    @Order(4)
    void setPackets2() {
        Institution institution = instDao.findInstitutionByEmail(emails[1]);
        assertNotNull(institution);
//        int minCount = 11;
//        int maxCount = 20;
        institution.setPositiveA_bagsCount(11);
        institution.setPositiveB_bagsCount(13);
        institution.setPositiveAB_bagsCount(15);
        institution.setPositiveO_bagsCount(17);

//        List<String> changes = new ArrayList<>();
//        changes.add("Ap");  changes.add("Bp");  changes.add("ABp"); changes.add("Op");
//        instDao.setChangedPacketCount(changes, institution);
    }

//  Here, only 'O' type is missing (rare blood type)
    @Test
    @Order(4)
    void setPackets3() {
        Institution institution = instDao.findInstitutionByEmail(emails[2]);
        assertNotNull(institution);
//        int minCount = 21;
//        int maxCount = 30;
        institution.setNegativeA_bagsCount(20);
        institution.setPositiveA_bagsCount(21);
        institution.setNegativeB_bagsCount(23);
        institution.setPositiveB_bagsCount(24);
        institution.setNegativeAB_bagsCount(26);
        institution.setPositiveAB_bagsCount(27);

//        List<String> changes = new ArrayList<>();
//        changes.add("Ap");  changes.add("Bp");  changes.add("ABp"); changes.add("An");  changes.add("Bn");  changes.add("ABn");
//        instDao.setChangedPacketCount(changes, institution);
    }

//  Email exists
    @Test
    @Order(4)
    void setPackets4() {
        Institution institution = instDao.findInstitutionByEmail(emails[1]);
        assertNotNull(institution);
    }

    @Test
    @Order(4)
    void setPackets5() {
        Institution institution = instDao.findInstitutionByEmail(emails[5]);
        assertNotNull(institution);
    }

//  Email does NOT exist, so we expect institution to be null
    @Test
    @Order(4)
    void setPackets6() {
        Institution institution = instDao.findInstitutionByEmail(emails[8]);
        assertNull(institution);
    }

    @Test
    @Order(7)
    void ifUserEmail0Exist_thenReturnTrue() {
        assertTrue(instDao.isInstitutionExistByEmail(emails[0]));
    }

    @Test
    @Order(7)
    void ifUserEmail1Exist_thenReturnTrue() {
        assertTrue(instDao.isInstitutionExistByEmail(emails[1]));
    }

    @Test
    @Order(7)
    void ifUserEmail2Exist_thenReturnTrue() {
        assertTrue(instDao.isInstitutionExistByEmail(emails[2]));
    }

    @Test
    @Order(7)
    void ifUserEmail3Exist_thenReturnTrue() {
        assertTrue(instDao.isInstitutionExistByEmail(emails[5]));
    }

    @Test
    @Order(7)
    void ifUserEmail7Exist_thenReturnFalse() {
        assertFalse(instDao.isInstitutionExistByEmail(emails[7]));
    }

    @Test
    @Order(7)
    void ifUserEmail8Exist_thenReturnFalse() {
        assertFalse(instDao.isInstitutionExistByEmail(emails[8]));
    }

    @Test
    @Order(7)
    void updatePasswordUser0_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(instDao.updatePassword(emails[0], newPassword));
        assertEquals(newPassword, instDao.findInstitutionByEmail(emails[0]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser1_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(instDao.updatePassword(emails[1], newPassword));
        assertEquals(newPassword, instDao.findInstitutionByEmail(emails[1]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser2_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertTrue(instDao.updatePassword(emails[2], newPassword));
        assertEquals(newPassword, instDao.findInstitutionByEmail(emails[2]).getPassword());
    }

    @Test
    @Order(7)
    void updatePasswordUser7_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertFalse(instDao.updatePassword(emails[7], newPassword));
    }

    @Test
    @Order(7)
    void updatePasswordUser8_UserExists_thenReturnTrueAndCheckPassword() {
        String newPassword = random.generatePassword(30);
        assertFalse(instDao.updatePassword(emails[8], newPassword));
    }


}