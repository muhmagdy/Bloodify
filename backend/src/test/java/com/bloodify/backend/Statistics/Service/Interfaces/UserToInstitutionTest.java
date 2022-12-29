package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.InstitutionManagement.model.UserToInstDonation;
import com.bloodify.backend.InstitutionManagement.repository.classes.UserToInstDonDAOImp;
import com.bloodify.backend.InstitutionManagement.repository.interfaces.UserToInstDonRepository;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.UserToInstitutionRandomizer;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


@SpringBootTest
class UserToInstitutionTest {

    @Resource(name = "userToInstDonDAOImp")
    UserToInstDonDAOImp transactionDao;

    @Resource(name = "institutionDAOImp")
//    @Mock
    private InstitutionDAO instDao;

    @Autowired
    UserToInstitution transactions;

    @Autowired
//    @Resource(name = "PostRepository")
    InstitutionRepository instRepository;

    @Autowired
    UserToInstDonRepository userToInstRepository;


    UserToInstitutionRandomizer randomTransaction = new UserToInstitutionRandomizer();
    int nTransactions = 44;
    LocalDate[] dates = new LocalDate[nTransactions];
    int[] years = new int[nTransactions];
    String[] bloodTypeNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    String[] nationalIDs = new String[nTransactions];
    Institution[] institutions = new Institution[6];

    int chosenIndexes = 0;
    int[] bagsCount = new int[8];
//    double[] bagsPercents = new double[8];
    int totalCollectedBags = 0;


    public void randomConstructor(int startYear, int endYear) {
        int satisfyingRange = 0;

//      preparing bags
        for(int i=0; i< dates.length; i++) {
            years[i] = (int)(Math.random()*10 + 2011);
            if(endYear == years[i])
                dates[i] = LocalDate.of(years[i], 12, 31);
            else
                dates[i] = LocalDate.of(years[i], 1, 1);

            if(years[i] >= startYear && years[i] <= endYear) {
                satisfyingRange++;
            }
        }
        int j=0;

        for(int i=0; i<years.length; i++) {
            if(years[i] >= startYear && years[i] <= endYear) {
                chosenIndexes++;
                bagsCount[i%8]++;
                totalCollectedBags++;
            }
        }
//        for(int i=0; i<chosenIndexes; i++) {
//            bagsPercents[i%8] = (double)bagsCount[i%8] / totalCollectedBags * 100;
//        }
//      Storing 6 random institutions
        RandomUserGenerations generations = new RandomUserGenerations();
        for(int i=0; i<institutions.length; i++) {
            institutions[i] = generations.generateRandomInstitution();
            instDao.saveInstitution(institutions[i]);
        }
//      Storing transactions
        for(int i=0; i<nTransactions; i++) {
            UserToInstDonation transaction = randomTransaction.UserToInstitutionRandomizer();
            transaction.setInstitution(institutions[2]);
            transaction.setDonationDate(dates[i]);
            transaction.setBloodType(bloodTypeNames[i%8]);
            transactionDao.save(transaction);
        }
        System.out.println();

    }

    @AfterEach
    public void tear_down(){
        this.instRepository.deleteAll();
        this.userToInstRepository.deleteAll();
    }


    @Test
    void bagsGotFromTransaction0() {
        randomConstructor(2015, 2019);
        int order = 0;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction1() {
        randomConstructor(2015, 2019);
        int order = 1;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction2() {
        randomConstructor(2015, 2019);
        int order = 2;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction3() {
        randomConstructor(2015, 2019);
        int order = 3;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction4() {
        randomConstructor(2015, 2019);
        int order = 4;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction5() {
        randomConstructor(2015, 2019);
        int order = 5;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction6() {
        randomConstructor(2015, 2019);
        int order = 6;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
    @Test
    void bagsGotFromTransaction7() {
        randomConstructor(2015, 2019);
        int order = 7;
        BloodBagsCountWrapper expected = new BloodBagsCountWrapper(
                bloodTypeNames[order], bagsCount[order]);

        BloodBagsCountWrapper[] result = transactions.bagsGotFromTransaction(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2019, 12, 31),
                instDao.findInstitutionByEmail(institutions[2].getEmail()).getEmail());

        assertEquals(expected.bagsCount, result[order].bagsCount);
//        assertEquals(expected.bagsPercentage, result[order].bagsPercentage);

    }
}