package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Institution.BloodBagsCountImpl;
import com.bloodify.backend.Statistics.Service.Post.PostsAndBTNumbersImpl;
import com.bloodify.backend.Statistics.Service.Post.PostsStatisticsWrapper;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.UserRequests.service.entities.PostDaoImp;
import com.bloodify.backend.UserRequests.service.interfaces.PostDao;
import com.bloodify.backend.userRequestTests.Randomizer;
import jakarta.annotation.Resource;
import lombok.NonNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsAndBTNumbersTest {

    @Autowired
//    @Resource(name = "PostRepository")
    PostRepository postRepository;

    @Resource(name = "userDAOImp")
//    @Mock
    UserDAO userDAO;

    @Resource(name = "institutionDAOImp")
//    @Mock
    private InstitutionDAO instDao;

    @Autowired
    PostsAndBTNumbersImpl target;

    int[] bagsCounts = {5, 3, 7, 6, 4, 2, 9, 6};

    int postsNumber = 20;
    int bagsNumber = 0;

    @AfterEach
    public void tear_down(){
        this.postRepository.deleteAll();
    }

    Randomizer random = new Randomizer();
    int[] randomNumbers = new int[postsNumber];
    Post[] posts = new Post[postsNumber];

    String[] bloodTypeNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};

    BloodBagsCountWrapper[] wrapActualAnswer = new BloodBagsCountWrapper[8];

    private final RandomUserGenerations userGenerations = new RandomUserGenerations();


    public void randomConstructor() {
        for(int i=0; i<randomNumbers.length; i++) {
            randomNumbers[i] = (int) (Math.random() * 15);
            bagsNumber += randomNumbers[i];
        }
        for(int i=0; i<posts.length; i++) {

            User user = userGenerations.generateRandomUser();
            Institution institution = random.generateRandomInstitution();
            Post post = new Post(user, institution, randomNumbers[i], LocalDateTime.now(), bloodTypeNames[i%8]);
            this.userDAO.saveUser(user);
            this.instDao.saveInstitution(institution);
            this.postRepository.save(post);
            posts[i] = post;

        }
        wrapActualAnswer = target.postsNumber();
    }

    @Test
    void postsNumbersAp() {
        randomConstructor();
        int order = 0;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("A+", totalBagsOfThisType);
        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersAn() {
        randomConstructor();
        int order = 1;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("A-", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersBp() {
        randomConstructor();
        int order = 2;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("B+", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersBn() {
        randomConstructor();
        int order = 3;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("B-", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersOp() {
        randomConstructor();
        int order = 4;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("O+", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersOn() {
        randomConstructor();
        int order = 5;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("O-", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersABp() {
        randomConstructor();
        int order = 6;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("AB+", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }

    @Test
    void postsNumbersABn() {
        randomConstructor();
        int order = 7;
        int totalBagsOfThisType = 0;
        while(order < postsNumber) {
            totalBagsOfThisType += randomNumbers[order];
            order += 8;
        }

        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("AB-", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }




}
