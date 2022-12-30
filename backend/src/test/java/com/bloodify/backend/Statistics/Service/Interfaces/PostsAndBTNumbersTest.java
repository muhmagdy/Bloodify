package com.bloodify.backend.Statistics.Service.Interfaces;

import com.bloodify.backend.AccountManagement.dao.helpingMethods.RandomUserGenerations;
import com.bloodify.backend.AccountManagement.dao.interfaces.InstitutionDAO;
import com.bloodify.backend.AccountManagement.dao.interfaces.UserDAO;
import com.bloodify.backend.AccountManagement.model.entities.Institution;
import com.bloodify.backend.AccountManagement.model.entities.User;
import com.bloodify.backend.Statistics.Service.Common.BloodBagsCountWrapper;
import com.bloodify.backend.Statistics.Service.Post.BloodBagsNumbersInPostsImpl;
import com.bloodify.backend.UserRequests.model.entities.Post;
import com.bloodify.backend.UserRequests.repository.interfaces.PostRepository;
import com.bloodify.backend.userRequestTests.Randomizer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostsAndBTNumbersTest {

    @Autowired
    PostRepository postRepository;

    @Resource(name = "userDAOImp")
    UserDAO userDAO;

    @Resource(name = "institutionDAOImp")
    private InstitutionDAO instDao;

    @Autowired
    BloodBagsNumbersInPostsImpl target;

    int postsNumber = 20;
    int bagsNumber = 0;

    @AfterEach
    public void tear_down(){
        this.postRepository.deleteAll();
    }

    Randomizer random = new Randomizer();
    int[] randomNumbers = new int[postsNumber];
    Post[] posts = new Post[postsNumber];
    User[] users = new User[postsNumber];
    Institution[] institutions = new Institution[1];
    String[] bloodTypeNames = {"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"};
    BloodBagsCountWrapper[] wrapActualAnswer = new BloodBagsCountWrapper[8];

    private final RandomUserGenerations userGenerations = new RandomUserGenerations();


    public void randomConstructor() {
        for(int i=0; i<randomNumbers.length; i++) {
            randomNumbers[i] = (int) (Math.random() * 15);
            bagsNumber += randomNumbers[i];
        }
        institutions[0] = random.generateRandomInstitution();
        this.instDao.saveInstitution(institutions[0]);
        for(int i=0; i<posts.length; i++) {
            users[i] = userGenerations.generateRandomUser();
        }
        for(int i=0; i<posts.length; i++) {
            Post post = new Post(users[i], institutions[0], randomNumbers[i], LocalDateTime.now(), bloodTypeNames[i%8]);
            this.userDAO.saveUser(users[i]);
            this.postRepository.save(post);
            posts[i] = post;
        }
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
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
        wrapActualAnswer = target.postsNumber(institutions[0].getEmail());
        BloodBagsCountWrapper wrapExpected = new BloodBagsCountWrapper("AB-", totalBagsOfThisType);

        order %= 8;
//        assertEquals(wrapExpected.postsCount, wrapActualAnswer[order].postsCount);
//        assertEquals(wrapExpected.postsPercentage, wrapActualAnswer[order].postsPercentage);
        assertEquals(wrapExpected.bagsCount, wrapActualAnswer[order].bagsCount);
//        assertEquals(wrapExpected.bagsPercentage, wrapActualAnswer[order].bagsPercentage);
    }




}
