package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserRepository;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.InstitutionManagement.model.InstToUserDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.InstitutionManagement.model.UserToUserDonation;
import com.bloodify.backend.InstitutionManagement.repository.classes.InstToUserDonDAOImp;
import com.bloodify.backend.InstitutionManagement.repository.classes.UserToInstDonDAOImp;
import com.bloodify.backend.InstitutionManagement.repository.classes.UserToUserDonDAOImp;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.InstToUserDonRepository;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonRepository;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonDAO;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToUserDonRepository;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.UserToInstitutionRandomizer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AllThreeTypesTest {

    @Resource(name = "userToInstDonDAOImp")
    UserToInstDonDAOImp userToInstDao;

    @Resource(name = "userToUserDonDAOImp")
    UserToUserDonDAOImp userToUserDao;

    @Resource(name = "instToUserDonDAOImp")
    InstToUserDonDAOImp instToUserDao;

    @Resource(name = "institutionDAOImp")
//    @Mock
    private InstitutionDAO instDao;

    @Resource(name = "userDAOImp")
    private UserDAO userDao;

    @Autowired
    AllThreeTypes transactions;

    @Autowired
    InstitutionRepository instRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserToInstDonRepository userToInstRepository;

    @Autowired
    UserToUserDonRepository userToUserRepository;

    @Autowired
    InstToUserDonRepository instToUserDonRepository;


    UserToInstitutionRandomizer randomTransaction = new UserToInstitutionRandomizer();
    int nTransactions = 44;
    LocalDate[] dates = new LocalDate[nTransactions];
    int[] years = new int[nTransactions];
    String[] bloodTypeNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
//    String[] nationalIDs = new String[nTransactions];
    Institution[] institutions = new Institution[6];
    User[] users = new User[8];

    int[] bloodBagsRequestedNum = new int[nTransactions];

    int chosenIndexes = 0;
    int[] bagsCount = new int[8];
    //    double[] bagsPercents = new double[8];
    int totalCollectedBags = 0;


    public void randomConstructor(int startYear, int endYear) {
//      preparing bags
        for(int i=0; i< dates.length; i++) {
            years[i] = (int)(Math.random()*10 + 2011);
            if(endYear == years[i])
                dates[i] = LocalDate.of(years[i], 12, 31);
            else
                dates[i] = LocalDate.of(years[i], 1, 1);
        }

        RandomUserGenerations generations = new RandomUserGenerations();
        for(int i=0; i<institutions.length; i++) {
            institutions[i] = generations.generateRandomInstitution();
            instDao.saveInstitution(institutions[i]);
        }
        for(int i=0; i<users.length; i++) {
            users[i] = generations.generateRandomUser();
            users[i].setBloodType(bloodTypeNames[i]);
            userDao.saveUser(users[i]);
        }
//      Storing transactions
        for(int i=0; i<nTransactions; i++) {
            UserToInstDonation userToInstTransaction = randomTransaction.UserToInstitutionRandomizer();
            userToInstTransaction.setInstitution(institutions[2]);
            userToInstTransaction.setDonationDate(dates[i]);
            userToInstTransaction.setBloodType(bloodTypeNames[i%8]);
            userToInstDao.save(userToInstTransaction);

            InstToUserDonation instToUserDonation = randomTransaction.InstitutionToUserRandomizer();
            bloodBagsRequestedNum[i] = (int)(Math.random()*5 + 1);
            instToUserDonation.setBagsCount(bloodBagsRequestedNum[i]);
            instToUserDonation.setBloodType(bloodTypeNames[i%8]);
            instToUserDonation.setInstitution(institutions[2]);
            instToUserDonation.setTransactionDate(dates[i]);
            instToUserDao.save(instToUserDonation);

            UserToUserDonation userToUserDonation = randomTransaction.UserToUserRandomizer();
            userToUserDonation.setAcceptor(users[i%8]);
            userToUserDonation.setDonationDate(dates[i]);
            userToUserDonation.setInstitution(institutions[2]);
            userToUserDao.save(userToUserDonation);
        }

        for(int i=0; i<years.length; i++) {
            if(years[i] >= startYear && years[i] <= endYear) {
                chosenIndexes++;
                bagsCount[i%8]+= 2 + bloodBagsRequestedNum[i];
                totalCollectedBags+= 2 + bloodBagsRequestedNum[i];
            }
        }

    }

//  metlassama, I don't understand why it gives unexpected errors! but I added a try catch to the
//  InstToUserDonDAOImp requestedBagsByTypeAndDate method to avoid wrong results, but still don't know what's wrong!!
    @AfterEach
    public void tear_down(){
        this.instRepository.deleteAll();
//        this.userRepository.deleteAll();
        this.userToInstRepository.deleteAll();
        this.instToUserDonRepository.deleteAll();
//        this.userToUserRepository.deleteAll();
    }

    @Test
    void getAllTransactionsBagsCount() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);
//        randomConstructor(start, end);
        randomConstructor(2015, 2019);
        int order = 0;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount1() {
        randomConstructor(2015, 2019);
        int order = 1;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount2() {
        randomConstructor(2015, 2019);
        int order = 2;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount3() {
        randomConstructor(2015, 2019);
        int order = 3;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount4() {
        randomConstructor(2015, 2019);
        int order = 4;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount5() {
        randomConstructor(2015, 2019);
        int order = 5;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount6() {
        randomConstructor(2015, 2019);
        int order = 6;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }
    @Test
    void getAllTransactionsBagsCount7() {
        randomConstructor(2015, 2019);
        int order = 7;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.getAllTransactionsBagsCount(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
    }


}