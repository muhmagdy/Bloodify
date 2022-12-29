package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionRepository;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Institution.BloodBagsCountImpl;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BloodBagsCountTest {

//    @Resource(name = "institutionDAOImp")
    @Resource(name = "institutionDAOImp")
    private InstitutionDAO institutionDAO;

    @Autowired
    InstitutionRepository instRepository;

    @Autowired
    BloodBagsCount bloodBagsCount;

    int[] bagsCounts = {5, 3, 7, 6, 4, 2, 9, 6};

    static int totalBagsNumber = 42;

    @AfterEach
    public void tear_down(){
        this.instRepository.deleteAll();
    }


    public void randomConstructor() {
        BloodBagsCountTest test = new BloodBagsCountTest();
        Institution institution = new Institution(
                "inst1@yahoo.com", "instName", "password", "blabla", 8
        );
        institutionDAO.saveInstitution(institution);

        institutionDAO.updateBagsCount(institution.getEmail(), "A+", 5);
        institutionDAO.updateBagsCount(institution.getEmail(), "A-", 3);
        institutionDAO.updateBagsCount(institution.getEmail(), "B+", 7);
        institutionDAO.updateBagsCount(institution.getEmail(), "B-", 6);
        institutionDAO.updateBagsCount(institution.getEmail(), "O+", 4);
        institutionDAO.updateBagsCount(institution.getEmail(), "O-", 2);
        institutionDAO.updateBagsCount(institution.getEmail(), "AB+", 9);
        institutionDAO.updateBagsCount(institution.getEmail(), "AB-", 6);

    }

    @Test
    void getAllCounts() {
        randomConstructor();
        Institution institution = institutionDAO.findInstitutionByEmail("inst1@yahoo.com");
        String[] bloodTypesNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

        BloodBagsCountWrapper[] resultWrapper = bloodBagsCount.getAllCounts(institution.getEmail());

        BloodBagsCountWrapper[] expectedWrapper = new BloodBagsCountWrapper[8];
        for(int i=0; i<8; i++) {
            BloodBagsCountWrapper ans = new BloodBagsCountWrapper();
            ans.bagsCount = bagsCounts[i];
//            ans.bagsPercentage = (double)bagsCounts[i] / totalBagsNumber * 100;
            ans.bloodType = bloodTypesNames[i];
            expectedWrapper[i] = ans;
        }

        for(int i=0; i<8; i++) {
            assertEquals(expectedWrapper[i].bagsCount, resultWrapper[i].bagsCount);
//            assertEquals(expectedWrapper[i].bagsPercentage, resultWrapper[i].bagsPercentage);
        }
    }
}
